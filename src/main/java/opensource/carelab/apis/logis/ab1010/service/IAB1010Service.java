package opensource.carelab.apis.logis.ab1010.service;

public interface IAB1010Service {
    Object selectReagentList(String token, int pageOffset, int pageLimit, String search, int searchFg, String fromDt, String toDt) throws Exception;
}
