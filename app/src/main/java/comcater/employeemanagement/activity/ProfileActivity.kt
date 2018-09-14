package comcater.employeemanagement.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import comcater.employeemanagement.R
import comcater.employeemanagement.adapter.AssignTaskAdapter
import comcater.employeemanagement.common.ClsGeneral
import comcater.employeemanagement.common.ConstantValue
import comcater.employeemanagement.model.AssignWorkModel
import comcater.employeemanagement.retrofitnetwork.DownlodableCallback
import comcater.employeemanagement.retrofitnetwork.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity: AppCompatActivity() {
    var retrofitDataProvider: RetrofitDataProvider?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        retrofitDataProvider = RetrofitDataProvider(this)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        getData()
    }

    private fun getData() {
        nameTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.NAME))
        emailTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.EMAIL))
        mobieTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.MOBILE))
        addressTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.ADDRESS))
        cityTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.CITY))
        stateTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.STATE))
        joinTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.JOININGATE))
        statusTVvalue.setText(ClsGeneral.getPreferences(this, ConstantValue.STATUS))
    }
}