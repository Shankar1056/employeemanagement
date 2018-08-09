package comcater.employeemanagement.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import comcater.employeemanagement.R
import comcater.employeemanagement.adapter.AssignTaskAdapter
import comcater.employeemanagement.common.ClsGeneral
import comcater.employeemanagement.common.ConstantValue
import comcater.employeemanagement.model.AssignWorkModel
import comcater.employeemanagement.retrofitnetwork.DownlodableCallback
import comcater.employeemanagement.retrofitnetwork.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_tasklist.*

class TaskListActivity: AppCompatActivity() {
    var retrofitDataProvider: RetrofitDataProvider?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasklist)
        retrofitDataProvider = RetrofitDataProvider(this)
        taskListRV.layoutManager = LinearLayoutManager(this)

        taskListApi()
    }

    private fun taskListApi() {
        retrofitDataProvider!!.getAssignTarget(ClsGeneral.getPreferences(this, ConstantValue.APITOKEN), ClsGeneral.getPreferences(this, ConstantValue.USERID), object : DownlodableCallback<AssignWorkModel> {
            override fun onSuccess(result: AssignWorkModel?) {

                if (result != null) {
                    taskListRV.adapter = AssignTaskAdapter(this@TaskListActivity, result.data!!)
                }
            }

            override fun onFailure(error: String?) { }

            override fun onUnauthorized(errorNumber: Int) { }
        })
    }
}