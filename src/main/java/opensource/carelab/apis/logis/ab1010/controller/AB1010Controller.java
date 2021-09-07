package opensource.carelab.apis.logis.ab1010.controller;

import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.annotation.ValidateToken;
import opensource.carelab.apis.logis.ab1010.service.impl.AB1010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ab1010")
public class AB1010Controller {
    @Autowired
    AB1010Service service;

//    @ValidateToken
    @GetMapping("/reagent")
    public Object selectReagentList(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "page_offset", defaultValue = "0") int pageOffset,
            @RequestParam(value = "page_limit", defaultValue = "30") int pageLimit,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(value = "search_fg", defaultValue = "1") int searchFg,
            @RequestParam(value = "from_dt") String fromDt,
            @RequestParam(value = "to_dt") String toDt
    ) throws Exception {
        return service.selectReagentList(token, pageOffset, pageLimit, search, searchFg, fromDt, toDt);
    }
}
