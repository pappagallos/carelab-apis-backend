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
    @GetMapping("/reagent")
    public Object selectReagentList(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(value = "page_count", defaultValue = "30") int pageCount,
            @RequestParam(required = false) String search
    ) throws Exception {
        return service.getReagentList(token, page, pageCount, search);
    }
}
