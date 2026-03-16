package br.com.erudio.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog(){
        logger.debug("Isso é um log DEBUG");
        logger.info("Isso é um log INFO");
        logger.warn("Isso é um log WARN");
        logger.error("Isso é um log ERROR");

        return "Logs gerados com sucesso!";
    }

}
