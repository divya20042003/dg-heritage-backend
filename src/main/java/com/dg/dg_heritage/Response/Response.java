package com.dg.dg_heritage.Response;

public class Response {


	    private String message;
	    private Object data;
	    private int statusCode;
	    private String status;

	    public Response() {
	    }

	    public Response(String message, Object data, int statusCode, String status) {
	        this.message = message;
	        this.data = data;
	        this.statusCode = statusCode;
	        this.status = status;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public Object getData() {
	        return data;
	    }

	    public void setData(Object data) {
	        this.data = data;
	    }

	    public int getStatusCode() {
	        return statusCode;
	    }

	    public void setStatusCode(int statusCode) {
	        this.statusCode = statusCode;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	}
