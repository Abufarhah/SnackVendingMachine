package com.freightos.snackvendingmachine.controllers.responses;

import java.io.Serializable;

public class APIResponseBody implements Serializable {
    private Object object;

    public APIResponseBody() {
    }

    public APIResponseBody(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
