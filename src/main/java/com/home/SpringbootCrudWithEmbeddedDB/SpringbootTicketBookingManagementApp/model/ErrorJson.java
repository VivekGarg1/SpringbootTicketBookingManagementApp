package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model;

import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.controller.CustomErrorHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ErrorJson {

    private static final Logger logger= LoggerFactory.getLogger(ErrorJson.class);

    private Integer status;
    private String message;
    private String error;
    private String timeStamp;
    private String trace;

    public ErrorJson(int status, Map<String, Object> errorAttribute) {
        this.status=status;
        this.error= (String) errorAttribute.get("error");
        this.message= (String) errorAttribute.get("message");
        this.timeStamp= (String) errorAttribute.get("timeStamp");
        this.trace= (String) errorAttribute.get("trace");
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getTrace() {
        return trace;
    }
}
