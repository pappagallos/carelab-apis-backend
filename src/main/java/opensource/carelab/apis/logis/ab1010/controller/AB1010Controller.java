package opensource.carelab.apis.logis.ab1010.controller;

import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.annotation.ValidateToken;
import opensource.carelab.apis.config.security.JwtTokenProvider;
import opensource.carelab.apis.logis.ab1010.model.P_AB1010SelectReagentList;
import opensource.carelab.apis.logis.ab1010.model.R_AB1010SelectReagentList;
import opensource.carelab.apis.logis.ab1010.service.impl.AB1010Service;
import opensource.carelab.common.context.userContext.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam(value = "page_offset", defaultValue = "0") int pageOffset,
            @RequestParam(value = "page_limit", defaultValue = "30") int pageLimit,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(value = "search_fg", defaultValue = "1") int searchFg,
            @RequestParam(value = "from_dt") String fromDt,
            @RequestParam(value = "to_dt") String toDt
    ) throws Exception {
        JwtTokenProvider tokenProvider = new JwtTokenProvider();
        UserContext userContext = tokenProvider.fetchUserContextFromJws(token);

        P_AB1010SelectReagentList params = new P_AB1010SelectReagentList();

        params.setUserEmail(userContext.getUserEmail());
        params.setPageOffset(pageOffset);
        params.setPageLimit(pageLimit);
        params.setSearch(search);
        params.setSearchFg(searchFg);
        params.setFromDt(fromDt);
        params.setToDt(toDt);

        return service.getReagentList(params);
    }
}
