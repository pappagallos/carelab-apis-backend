package opensource.carelab.apis.user.mapper;

import opensource.carelab.apis.user.model.P_IsDuplicateEmail;
import opensource.carelab.apis.user.model.P_SignUpData;
import opensource.carelab.apis.user.model.R_IsDuplicateEmail;
import opensource.carelab.apis.user.model.R_SignUpData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {
    R_SignUpData insertUser(P_SignUpData params) throws Exception;
    R_IsDuplicateEmail isDuplicateEmail(P_IsDuplicateEmail params) throws Exception;
}
