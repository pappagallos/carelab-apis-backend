package opensource.carelab.apis.user.service.impl;

import opensource.carelab.apis.user.mapper.IUserMapper;
import opensource.carelab.apis.user.model.P_IsDuplicateEmail;
import opensource.carelab.apis.user.model.P_SignUpData;
import opensource.carelab.apis.user.service.IUserService;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

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
}