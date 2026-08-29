package com.dg.dg_heritage.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dg.dg_heritage.Response.Response;
import com.dg.dg_heritage.Response.ResponseHelper;
import com.dg.dg_heritage.admin.Service.AdminService;
import com.dg.dg_heritage.admin.model.Login;
import com.dg.dg_heritage.util.Constant;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
	 @PostMapping(value = "/login", produces = "application/json")
	    public Response login(@RequestBody Login request, HttpServletRequest httpServletRequest) {
		 try {

		        Response res = adminService.login(request);

		        if (res.getData() != null) {

		            // 👉 store session
		            httpServletRequest.getSession().setAttribute("ADMIN", res.getData());

		        }

		        return res;

		    } catch (Exception e) {
		        e.printStackTrace();

		        return ResponseHelper.getErrorResponse(
		                "Internal Server Error",
		                null,
		                Constant.STATUS_CODE_SERVER_ERROR,
		                "ERROR"
		        );
		    }
	    }
	 @PostMapping("/logout")
	 public Response logout(HttpServletRequest request) {
	     request.getSession().invalidate(); // destroy session

	     return ResponseHelper.getSuccessResponse(
	             "Logged out successfully",
	             null,
	             Constant.STATUS_CODE_SUCCESS,
	             "SUCCESS"
	     );
	 }

}
