package opensource.carelab.apis.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.config.security.JwtTokenProvider;
import opensource.carelab.apis.logis.ab1010.model.P_AB1010SelectReagentList;
import opensource.carelab.apis.user.mapper.IUserMapper;
import opensource.carelab.apis.user.model.*;
import opensource.carelab.apis.user.service.IUserService;
import opensource.carelab.common.context.userContext.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {
    @Autowired
    IUserMapper mapper;

    @Override
    public Object insertUser(P_InsertUser params) throws Exception {
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
        Optional<R_SelectUser> userInfo = Optional.ofNullable(mapper.selectUser(params));

        // [2-1] 사용자가 입력한 사용자가 데이터베이스 내에 있을 경우
        if (userInfo.isPresent()) {
            // [3] 사용자가 입력한 비밀번호가 데이터베이스에 저장된 BCrypt와 일치할 경우 if문 실행
            boolean isValid = BCrypt.checkpw(params.getPassword(), userInfo.get().getUserPassword());
            if (isValid) {
                // [3-1] JWTTokenProvider를 생성한 뒤
                JwtTokenProvider provider = new JwtTokenProvider();

                // [3-2] userContext 생성하여 userInfo의 값을 모두 세팅해주고
                UserContext userContext = new UserContext();
                userContext.setUserEmail(userInfo.get().getUserEmail());
                userContext.setUserName(userInfo.get().getUserName());
                userContext.setUserPhone(userInfo.get().getUserPhone());
                userContext.setUserStatus(userInfo.get().getUserStatus());
                userContext.setUserLevel(userInfo.get().getUserLevel());

                // [3-3] 생성한 JWT 토큰을 userContext에 세팅
                userContext.setToken(provider.createToken(userContext));
                log.info("[UserService.selectUser] userContext : {}", userContext);

                return userContext;
            } else {
                return "incorrect email or password.";
            }
            // [2-2] 사용자가 입력한 사용자가 데이터베이스 내에 없을 경우
        } else {
            return "not exist user.";
        }
    }

    @Override
    public Object deleteUser(String token, String email) throws Exception {
        // [1] JwtTokenProvider 로부터 userContext를 전달받기 위해 객체 생성
        JwtTokenProvider provider = new JwtTokenProvider();
        UserContext userContext = provider.fetchUserContextFromJws(token);

        // [2] 가져온 userContext를 P_DeleteUser 객체에 담아 mapper로 전달하기 위해 객체 생성
        P_DeleteUser userInfo = new P_DeleteUser();
        userInfo.setEmail(userContext.getUserEmail());

        // [3] 만약 Jws 토큰의 subject의 userEmail 값과 파라미터로 받아온 email의 값이 동일하다면 올바른 사용자이므로 삭제 처리
        if (userInfo.getEmail().equals(email)) {
            return mapper.deleteUser(userInfo);
        }

        // [4] 만약 토큰과 userContext에서 가져온 email이 다르다면
        return "failed delete user.";
    }

    @Override
    public Object updateUser(String token, String email, P_UpdateUser params) throws Exception {
        // [1] JwtTokenProvider 로부터 userContext를 전달받기 위해 객체 생성
        JwtTokenProvider provider = new JwtTokenProvider();
        UserContext userContext = provider.fetchUserContextFromJws(token);

        // [2] 가져온 userContext를 P_UpdateUser 객체에 담아 mapper로 전달하기 위해 객체 생성
        P_UpdateUser userInfo = new P_UpdateUser();
        userInfo.setEmail(userContext.getUserEmail());
        userInfo.setName(params.getName());
        userInfo.setPassword(BCrypt.hashpw(params.getPassword(), BCrypt.gensalt()));
        userInfo.setPhone(params.getPhone());

        // [2-3] 만약 Jws 토큰의 subject의 userEmail 값과 파라미터로 받아온 email의 값이 동일하다면 올바른 사용자이므로 삭제 처리
        if (userContext.getUserEmail().equals(email)) {
            return mapper.updateUser(userInfo);
        }

        // [3] 만약 유효한 토큰이 아닐 경우 비정상적인 접근이므로 탈출
        return "failed update user.";
    }

}