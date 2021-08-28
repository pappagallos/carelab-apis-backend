package opensource.carelab.apis.user.controller;

import opensource.carelab.apis.user.model.P_SignUpData;
import opensource.carelab.apis.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/signup")
    public Object signUp(@RequestBody P_SignUpData params) throws Exception {
        return service.insertUser(params);
    }
}
