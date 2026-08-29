package com.dg.dg_heritage.admin.DAO;

import org.springframework.web.multipart.MultipartFile;

import com.dg.dg_heritage.Response.Response;
import com.dg.dg_heritage.admin.model.AdminLogin;
import com.dg.dg_heritage.admin.model.Login;

public interface AdminDAO {

	AdminLogin login(Login request);

	void updateLastLogin(Long adminId);

	Response createProduct(String payload, String imageMeta, MultipartFile[] images);

	Response updateProduct(Long id, String payload, String imageMeta, MultipartFile[] images);

	Response getCategories();

}
