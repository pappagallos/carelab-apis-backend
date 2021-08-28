package opensource.carelab.apis.user.service;

import opensource.carelab.apis.user.model.P_SelectUser;
import opensource.carelab.apis.user.model.P_SignUpData;

public interface IUserService {
    Object insertUser(P_SignUpData params) throws Exception;
    Object selectUser(P_SelectUser params) throws Exception;
}
