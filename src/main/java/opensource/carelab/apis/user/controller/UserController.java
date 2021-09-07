package opensource.carelab.apis.user.controller;

import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.annotation.ValidateToken;
import opensource.carelab.apis.user.model.P_SelectUser;
import opensource.carelab.apis.user.model.P_InsertUser;
import opensource.carelab.apis.user.model.P_UpdateUser;
import opensource.carelab.apis.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/signup")
    public Object signUp(@RequestBody P_InsertUser params) throws Exception {
        return service.insertUser(params);
    }

    @PostMapping("/signin")
    public Object signIn(@RequestBody P_SelectUser params) throws Exception {
        return service.selectUser(params);
    }

    @ValidateToken
    @DeleteMapping("/{email}")
    public Object deleteAccount(@RequestHeader("Authorization") String token, @PathVariable String email) throws Exception {
        return service.deleteUser(token, email);
    }

    @ValidateToken
    @PutMapping("/{email}")
    public Object updateAccount(@RequestHeader("Authorization") String token, @PathVariable String email, @RequestBody P_UpdateUser params) throws Exception {
        return service.updateUser(token, email, params);
    }
}
