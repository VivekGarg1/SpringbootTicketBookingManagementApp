package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.controller;

import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.SpringbootTicketBookingManagementAppApplication;
import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model.ErrorJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class CustomErrorHandlerController implements ErrorController {

    private static final Logger logger= LoggerFactory.getLogger(CustomErrorHandlerController.class);
    private static final String PATH = "/error";

    @Value("${debug}")
    private boolean debug;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    ErrorJson error(HttpServletRequest request, WebRequest webRequest,HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring.
        // Here we just define response body.
        return new ErrorJson(response.getStatus(), getErrorAttributes(webRequest, debug));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        return errorAttributes.getErrorAttributes(webRequest,includeStackTrace);
    }
}
