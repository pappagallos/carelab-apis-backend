package opensource.carelab.apis.user.controller;

import opensource.carelab.apis.user.model.P_SelectUser;
import opensource.carelab.apis.user.model.P_SignUpData;
import opensource.carelab.apis.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/signup")
    public Object signUp(@RequestBody P_SignUpData params) throws Exception {
        return service.insertUser(params);
    }

    @PostMapping("/signin")
    public Object signUp(@RequestBody P_SelectUser params) throws Exception {
        return service.selectUser(params);
    }
}
