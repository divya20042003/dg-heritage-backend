package com.dg.dg_heritage.Response;

public class ResponseHelper {

	    public static Response getSuccessResponse(
	            String message,
	            Object data,
	            int statusCode,
	            String status) {

	        return new Response(
	                message,
	                data,
	                statusCode,
	                status
	        );
	    }

	    public static Response getErrorResponse(
	            String message,
	            Object data,
	            int statusCode,
	            String status) {

	        return new Response(
	                message,
	                data,
	                statusCode,
	                status
	        );
	    }
	}

