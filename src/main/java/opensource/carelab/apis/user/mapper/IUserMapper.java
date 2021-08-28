package opensource.carelab.apis.user.mapper;

import opensource.carelab.apis.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IUserMapper {
    R_SignUpData insertUser(P_SignUpData params) throws Exception;
    R_IsDuplicateEmail isDuplicateEmail(P_IsDuplicateEmail params) throws Exception;
    R_SelectUser selectUser(P_SelectUser params) throws Exception;
}
