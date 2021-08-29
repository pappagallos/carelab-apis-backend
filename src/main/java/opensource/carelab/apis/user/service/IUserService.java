package opensource.carelab.apis.user.service;

import opensource.carelab.apis.user.model.P_SelectUser;
import opensource.carelab.apis.user.model.P_InsertUser;
import opensource.carelab.apis.user.model.P_UpdateUser;

public interface IUserService {
    Object insertUser(P_InsertUser params) throws Exception;
    Object selectUser(P_SelectUser params) throws Exception;
    Object deleteUser(String token, String email) throws Exception;
    Object updateUser(String token, String email, P_UpdateUser params) throws Exception;
}
