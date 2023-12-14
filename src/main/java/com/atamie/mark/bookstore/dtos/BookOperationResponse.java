package com.atamie.mark.bookstore.dtos;

public class BookOperationResponse {

    private String status;
    private String message;
    private Object responseDetails;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResponseDetails(Object responseDetails) {
        this.responseDetails = responseDetails;
    }

    public Object getResponseDetails() {
        return responseDetails;
    }
}
