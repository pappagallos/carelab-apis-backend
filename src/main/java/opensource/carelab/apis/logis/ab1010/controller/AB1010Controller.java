package opensource.carelab.apis.logis.ab1010.controller;

import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.annotation.ValidateToken;
import opensource.carelab.apis.logis.ab1010.service.impl.AB1010Service;
import opensource.carelab.common.context.userContext.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ab1010")
public class AB1010Controller {
    @Autowired
    AB1010Service service;

    @ValidateToken
    @GetMapping("/items")
    public Object getItems(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam int page,
            @RequestParam(value = "page_count") int pageCount,
            @RequestParam String search
    ) throws Exception {
        UserContext userContext = new UserContext();

        return userContext;
    }
}
