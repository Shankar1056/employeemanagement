package comcater.employeemanagement.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import comcater.employeemanagement.R
import comcater.employeemanagement.activity.MainActivity
import comcater.employeemanagement.common.ClsGeneral
import comcater.employeemanagement.common.ConstantValue
import comcater.employeemanagement.model.UserModel
import comcater.employeemanagement.retrofitnetwork.DownlodableCallback
import comcater.employeemanagement.retrofitnetwork.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    var retrofitDataProvider: RetrofitDataProvider?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        retrofitDataProvider = RetrofitDataProvider(this)
        if (ClsGeneral.getPreferences(this, ConstantValue.USERID).equals("")){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else {
            submitButton.setOnClickListener {
                if (input_email.text.toString().trim().equals("")) Toast.makeText(this, resources.getString(R.string.enteremail), Toast.LENGTH_SHORT).show()
                else if (input_password.text.toString().trim().equals("")) Toast.makeText(this, resources.getString(R.string.enterpassword), Toast.LENGTH_SHORT).show()
                else doLogin()
            }
        }
    }

    private fun doLogin() {
        retrofitDataProvider!!.login(input_email.text.toString(), input_password.text.toString(), "", object : DownlodableCallback<UserModel> {
            override fun onSuccess(result: UserModel?) {
                if (result!!.status!!){
                    if (result.data!![0].status.equals("1")){
                        ClsGeneral.setPreferences(this@LoginActivity, ConstantValue.USERID, result.data[0].id)
                        ClsGeneral.setPreferences(this@LoginActivity, ConstantValue.APITOKEN, result.data[0].api_token)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }

            override fun onFailure(error: String?) {  }

            override fun onUnauthorized(errorNumber: Int) {  }
        })
    }
}