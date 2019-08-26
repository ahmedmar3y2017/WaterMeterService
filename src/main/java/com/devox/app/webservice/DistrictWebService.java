package com.devox.app.webservice;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@CrossOrigin
public class DistrictWebService {
    org.slf4j.Logger logger = LoggerFactory.getLogger(DistrictWebService.class);

}
