package opensource.carelab.apis.logis.ab1010.service.impl;

import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.config.security.JwtTokenProvider;
import opensource.carelab.apis.logis.ab1010.mapper.IAB1010Mapper;
import opensource.carelab.apis.logis.ab1010.model.P_AB1010SelectReagentList;
import opensource.carelab.apis.logis.ab1010.service.IAB1010Service;
import opensource.carelab.common.context.userContext.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AB1010Service implements IAB1010Service {
    @Autowired
    IAB1010Mapper mapper;

    @Override
    public Object selectReagentList(String token, int pageOffset, int pageLimit, String search, int searchFg, String fromDt, String toDt) throws Exception {
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

        log.info("[getReagentList] parameters : {}", params);

        return mapper.selectReagentList(params);
    }
}
