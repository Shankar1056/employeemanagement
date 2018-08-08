package comcater.employeemanagement.retrofitnetwork;


import comcater.employeemanagement.model.UserModel;

public interface ServiceMethods {

    void login(String email, String password, DownlodableCallback<UserModel> callback);

}
