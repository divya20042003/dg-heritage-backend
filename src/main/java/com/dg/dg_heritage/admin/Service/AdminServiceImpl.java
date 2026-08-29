package com.dg.dg_heritage.admin.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dg.dg_heritage.Response.Response;
import com.dg.dg_heritage.Response.ResponseHelper;
import com.dg.dg_heritage.admin.DAO.AdminDAO;
import com.dg.dg_heritage.admin.model.AdminLogin;
import com.dg.dg_heritage.admin.model.Login;
import com.dg.dg_heritage.util.Constant;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDAO adminDAO;

	@Override
	public Response login(Login request) {
		 AdminLogin admin = adminDAO.login(request);

	        if (admin != null) {

	            adminDAO.updateLastLogin(admin.getAdminId());

	            return ResponseHelper.getSuccessResponse(
	                    "Login Success",
	                    "Login Successfully",
	                    Constant.STATUS_CODE_SUCCESS,
	                    "SUCCESS"
	            );

	        } else {

	            return ResponseHelper.getErrorResponse(
	                    "Invalid Email or Password",
	                    null,
	                    Constant.STATUS_CODE_FAILED,
	                    "FAILED"
	            );
	        }
	    }

	@Override
	public Response createProduct(String payload, String imageMeta, MultipartFile[] images) {
		   try {
	            return adminDAO.createProduct(payload, imageMeta, images);

	        } catch (Exception e) {
	            e.printStackTrace();

	            return ResponseHelper.getErrorResponse(
	                    "Unable to create product",
	                    null,
	                    Constant.STATUS_CODE_SERVER_ERROR,
	                    "PRODUCT_CREATE_FAILED"
	            );
	        }
	}

	@Override
	public Response updateProduct(Long id, String payload, String imageMeta, MultipartFile[] images) {
		  try {
	            return adminDAO.updateProduct(id, payload, imageMeta, images);

	        } catch (Exception e) {
	            e.printStackTrace();

	            return ResponseHelper.getErrorResponse(
	                    "Unable to update product",
	                    null,
	                    Constant.STATUS_CODE_SERVER_ERROR,
	                    "PRODUCT_UPDATE_FAILED"
	            );
	        }
	}

	@Override
	public Response getCategories() {
        return adminDAO.getCategories();

	}
	}

