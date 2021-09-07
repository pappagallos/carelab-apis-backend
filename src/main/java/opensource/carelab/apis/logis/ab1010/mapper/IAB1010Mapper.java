package opensource.carelab.apis.logis.ab1010.mapper;

import opensource.carelab.apis.logis.ab1010.model.P_AB1010SelectReagentList;
import opensource.carelab.apis.logis.ab1010.model.R_AB1010SelectReagentList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAB1010Mapper {
    List<R_AB1010SelectReagentList> selectReagentList(P_AB1010SelectReagentList params) throws Exception;
}
