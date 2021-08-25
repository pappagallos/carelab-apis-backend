package opensource.carelab.apis.logis.ab1010.controller;

import opensource.carelab.apis.logis.ab1010.service.impl.AB1010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ab1010")
public class AB1010Controller {
    @Autowired
    AB1010Service service;

    @GetMapping("/get")
    public Object getData() throws Exception {
        return service.getData();
    }
}
