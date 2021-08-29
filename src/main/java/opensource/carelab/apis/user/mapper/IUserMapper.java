package opensource.carelab.apis.user.mapper;

import opensource.carelab.apis.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {
    R_InsertUser insertUser(P_InsertUser params) throws Exception;
    R_IsDuplicateEmail isDuplicateEmail(P_IsDuplicateEmail params) throws Exception;
    R_SelectUser selectUser(P_SelectUser params) throws Exception;
    int deleteUser(P_DeleteUser params) throws Exception;
    int updateUser(P_UpdateUser userInfo) throws Exception;
}
