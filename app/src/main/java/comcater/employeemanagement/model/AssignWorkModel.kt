package comcater.employeemanagement.model

import com.google.gson.annotations.SerializedName

class AssignWorkModel(
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("message")
        var message: String? = null,
        @SerializedName("data")
        var data: ArrayList<AssignWorkDataModel>? = null
)

class AssignWorkDataModel(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("employee_id")
        var employee_id: String? = null,
        @SerializedName("target_value")
        var target_value: String? = null,
        @SerializedName("target_period")
        var target_period: String? = null,
        @SerializedName("assign_date")
        var assign_date: String? = null,
        @SerializedName("status")
        var status: String? = null
)
