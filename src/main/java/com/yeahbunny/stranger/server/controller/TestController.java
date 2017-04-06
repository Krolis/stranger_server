package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.controller.dto.response.MyTestResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping(value = "/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);


    @ApiOperation(value = "Metoda testowa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful")}
    )
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public MyTestResponse testMethod(){

        LOG.debug("tiruriru log");
        return new MyTestResponse(69,"hello world");
    }

    @ApiOperation(value = "Metoda testowa, wymaga autoryzacji")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 403, message = "blad autentyfikacji")}
    )
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    @ResponseBody
    public MyTestResponse test403Method(){

        LOG.debug("zautoryzowany uzytkownik");
        return new MyTestResponse(12,"hello world zautentyfikowany uzytkowniku");
    }
}
