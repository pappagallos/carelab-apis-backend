package opensource.carelab.apis.logis.ab1010.service.impl;

import opensource.carelab.apis.logis.ab1010.mapper.IAB1010Mapper;
import opensource.carelab.apis.logis.ab1010.service.IAB1010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AB1010Service implements IAB1010Service {
    @Autowired
    IAB1010Mapper mapper;

    public Object getData() throws Exception {
        return mapper.getData();
    }
}
