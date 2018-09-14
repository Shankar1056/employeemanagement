package comcater.employeemanagement.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserModel(
@SerializedName("status")
val status: Boolean?=null,
@SerializedName("message")
val message: String?=null,
@SerializedName("data")
val data: ArrayList<UserDataModel>?=null
) : Parcelable

@Parcelize
class UserDataModel (
        @SerializedName("id")
        val id: String?=null,
        @SerializedName("name")
        val name: String?=null,
        @SerializedName("email_id")
        val email: String?=null,
        @SerializedName("mobile")
        val mobile: String?=null,
        @SerializedName("alternate_number")
        val alternate_number: String?=null,
        @SerializedName("address")
        val address: String?=null,
        @SerializedName("city")
        val city: String?=null,
        @SerializedName("state")
        val state: String?=null,
        @SerializedName("pincode")
        val pincode: String?=null,
        @SerializedName("previous_company_name")
        val previous_company_name: String?=null,
        @SerializedName("experiance")
        val experiance: String?=null,
        @SerializedName("api_token")
        val api_token: String?=null,
        @SerializedName("device_token")
        val device_token: String?=null,
        @SerializedName("joining_date")
        val joining_date: String?=null,
        @SerializedName("updated_on")
        val updated_on: String?=null,
        @SerializedName("status")
        val status: String?=null

) : Parcelable
