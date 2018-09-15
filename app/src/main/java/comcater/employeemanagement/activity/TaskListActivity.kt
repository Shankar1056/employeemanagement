package comcater.employeemanagement.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import comcater.employeemanagement.R
import comcater.employeemanagement.R.id.taskListRV
import comcater.employeemanagement.adapter.AssignTaskAdapter
import comcater.employeemanagement.common.ClsGeneral
import comcater.employeemanagement.common.ConstantValue
import comcater.employeemanagement.common.Utilz
import comcater.employeemanagement.model.AssignWorkModel
import comcater.employeemanagement.model.EmployeeLatLonModel
import comcater.employeemanagement.retrofitnetwork.DownlodableCallback
import comcater.employeemanagement.retrofitnetwork.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_tasklist.*

class TaskListActivity: AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    var callmethoEverySecond = false
    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onConnected(p0: Bundle?) {   }

    override fun onConnectionSuspended(p0: Int) { }

    var retrofitDataProvider: RetrofitDataProvider?= null
    var mFusedLocationClient: FusedLocationProviderClient? = null
    var mLocation: Location? = null
    val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    var RC_SIGN_IN = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasklist)
        retrofitDataProvider = RetrofitDataProvider(this)
        taskListRV.layoutManager = LinearLayoutManager(this)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        taskListApi()
        enableGPSAutoMatically()
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (callmethoEverySecond)
                    getLastLocation()     // this method will contain your almost-finished HTTP calls
                handler.postDelayed(this, 3000)
            }
        }, 3000)

    }

    private fun enableGPSAutoMatically() {
        var googleApiClient: GoogleApiClient? = null
        if (googleApiClient == null) {
            googleApiClient = GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build()
            googleApiClient!!.connect()
            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = (30 * 1000).toLong()
            locationRequest.fastestInterval = (5 * 1000).toLong()
            val builder = LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest)

            // **************************
            builder.setAlwaysShow(true) // this is the key ingredient
            // **************************

            val result = LocationServices.SettingsApi
                    .checkLocationSettings(googleApiClient, builder.build())
            result.setResultCallback { result ->
                val status = result.status
                val state = result
                        .locationSettingsStates
                when (status.statusCode) {
                    LocationSettingsStatusCodes.SUCCESS ->
                        //toast("Success");
                        initWidgit()
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        // toast("GPS is not on");
                        // Location settings are not satisfied. But could be
                        // fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling
                            // startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(this@TaskListActivity, 1000)

                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                        }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> toast("Setting change not allowed")
                }// Location settings are not satisfied. However, we have
                // no way to fix the
                // settings so we won't show the dialog.
            }
        } else {

        }
    }

    private fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                initWidgit()
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                finishAffinity()
            }
        }
    }

    private fun initWidgit() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mLocation = Location("")
        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)

        if (shouldProvideRationale) {

        } else {
            startLocationPermissionRequest()
        }
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this@TaskListActivity,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_PERMISSIONS_REQUEST_CODE)
    }

   override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                   grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size <= 0) {

            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation()
            } else {
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        mFusedLocationClient!!.getLastLocation()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && task.result != null) {
                        mLocation = task.result
                        saveLatLonToServer(mLocation!!.latitude, mLocation!!.longitude)
                    } else {
                    }
                }
    }

    private fun saveLatLonToServer(latitude: Double, longitude: Double) {
        callmethoEverySecond = true
        retrofitDataProvider!!.saveEmpLatLon(ClsGeneral.getPreferences(this, ConstantValue.APITOKEN), ClsGeneral.getPreferences(this, ConstantValue.USERID), latitude.toString(), longitude.toString(), Utilz.getCurrentTime(), ClsGeneral.getPreferences(this, ConstantValue.USERID), object : DownlodableCallback<EmployeeLatLonModel> {
            override fun onSuccess(result: EmployeeLatLonModel?) {
                mFusedLocationClient!!.flushLocations()
            }

            override fun onFailure(error: String?) {  }

            override fun onUnauthorized(errorNumber: Int) {    }

        })



    }

    private fun taskListApi() {
        retrofitDataProvider!!.getAssignTarget(ClsGeneral.getPreferences(this, ConstantValue.APITOKEN), ClsGeneral.getPreferences(this, ConstantValue.USERID), object : DownlodableCallback<AssignWorkModel> {
            override fun onSuccess(result: AssignWorkModel?) {

                if (result != null) {
                    taskListRV.adapter = AssignTaskAdapter(this@TaskListActivity, result.data!!)
                    Utilz.GetDateTimeDifference(Utilz.getDateFromString(), Utilz.getDateFromString(result.data!![0].assign_date))
                }
            }

            override fun onFailure(error: String?) { }

            override fun onUnauthorized(errorNumber: Int) { }
        })
    }
}