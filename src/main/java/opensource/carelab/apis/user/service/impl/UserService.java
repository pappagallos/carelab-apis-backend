package opensource.carelab.apis.user.service.impl;

import opensource.carelab.apis.config.security.JwtTokenProvider;
import opensource.carelab.apis.user.mapper.IUserMapper;
import opensource.carelab.apis.user.model.P_SelectUser;
import opensource.carelab.apis.user.model.P_SignUpData;
import opensource.carelab.apis.user.model.R_SelectUser;
import opensource.carelab.apis.user.service.IUserService;
import opensource.carelab.common.context.userContext.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserMapper mapper;

    @Override
    public Object insertUser(P_SignUpData params) throws Exception {
        // [1] 파라미터로부터 사용자가 입력한 비밀번호 가져오기
        String password = params.getPassword();

        // [2] 비밀번호를 BCrypt 암호화
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // [3] 파라미터에 다시 비밀번호 변경
        params.setPassword(encryptedPassword);

        // [4] 매퍼를 호출하여 사용자 추가
        return mapper.insertUser(params);
    }

    @Override
    public Object selectUser(P_SelectUser params) throws Exception {
        // [1] 파라미터로 전달받은 email 데이터를 mapper에게 전달해서 user 정보 가져오기
        List<R_SelectUser> userInfoList = mapper.selectUser(params);
        R_SelectUser userInfo;

        // [2] 사용자가 입력한 사용자가 데이터베이스 내에 없을 경우
        if (userInfoList.size() <= 0) {
            return "not exist user.";
        } else {
            userInfo = userInfoList.get(0);
        }

        // [3] 사용자가 입력한 비밀번호가 데이터베이스에 저장된 BCrypt와 일치할 경우 if문 실행
        boolean isValid = BCrypt.checkpw(params.getPassword(), userInfo.getUserPassword());
        if (isValid) {
            // [3-1] JWTTokenProvider를 생성한 뒤
            JwtTokenProvider provider = new JwtTokenProvider();

            // [3-2] userContext 생성하여 userInfo의 값을 모두 세팅해주고
            UserContext userContext = new UserContext();
            userContext.setUserEmail(userInfo.getUserEmail());
            userContext.setUserName(userInfo.getUserName());
            userContext.setUserPhone(userInfo.getUserPhone());
            userContext.setUserGroupCd(userInfo.getUserGroupCd());
            userContext.setUserStatus(userInfo.getUserStatus());
            userContext.setUserLevel(userInfo.getUserLevel());

            // [3-3] 생성한 JWT 토큰을 userContext에 세팅
            userContext.setToken(provider.createToken(userContext));

            return userContext;
        } else {
            return "incorrect email or password.";
        }
    }
}