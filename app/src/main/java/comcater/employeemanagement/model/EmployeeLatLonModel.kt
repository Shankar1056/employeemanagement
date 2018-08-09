package comcater.employeemanagement.model

import com.google.gson.annotations.SerializedName

class EmployeeLatLonModel (
    @SerializedName("status")
val status:String?= null,
    @SerializedName("data")
val data:String?= null,
    @SerializedName("message")
val message:String?= null
)