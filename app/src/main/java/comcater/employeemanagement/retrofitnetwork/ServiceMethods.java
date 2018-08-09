package comcater.employeemanagement.retrofitnetwork;


import comcater.employeemanagement.model.AssignWorkModel;
import comcater.employeemanagement.model.EmployeeLatLonModel;
import comcater.employeemanagement.model.UserModel;

public interface ServiceMethods {

    void login(String email, String password, String device_token, DownlodableCallback<UserModel> callback);
    void saveEmpLatLon(String api_token, String emp_id, String lat, String lon, String address, DownlodableCallback<EmployeeLatLonModel> callback);
    void GetAssignTarget(String api_token, String emp_id, DownlodableCallback<AssignWorkModel> callback);

}
