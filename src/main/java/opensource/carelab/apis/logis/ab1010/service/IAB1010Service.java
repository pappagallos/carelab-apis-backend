package opensource.carelab.apis.logis.ab1010.service;

import opensource.carelab.apis.logis.ab1010.model.P_AB1010SelectReagentList;

public interface IAB1010Service {
    Object getReagentList(P_AB1010SelectReagentList params) throws Exception;
}
