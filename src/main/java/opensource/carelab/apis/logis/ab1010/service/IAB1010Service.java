package opensource.carelab.apis.logis.ab1010.service;

public interface IAB1010Service {
    Object getReagentList(String token, int page, int pageCount, String search) throws Exception;
}
