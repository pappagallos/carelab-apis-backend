package opensource.carelab.apis.logis.ab1010.service.impl;

import lombok.extern.slf4j.Slf4j;
import opensource.carelab.apis.logis.ab1010.mapper.IAB1010Mapper;
import opensource.carelab.apis.logis.ab1010.model.P_AB1010SelectReagentList;
import opensource.carelab.apis.logis.ab1010.service.IAB1010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AB1010Service implements IAB1010Service {
    @Autowired
    IAB1010Mapper mapper;
    @Override
    public Object getReagentList(String token, int page, int pageCount, String search) throws Exception {
        P_AB1010SelectReagentList params = new P_AB1010SelectReagentList();

        params.setPage(page);
        params.setPageCount(pageCount);
        params.setPageOffset(page * pageCount);
        params.setSearch(search);

        log.info("[getReagentList] parameters : " + params.toString());

        return mapper.getReagentList(params);
    }
}
