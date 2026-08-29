package com.dg.dg_heritage.admin.Service;

import org.springframework.web.multipart.MultipartFile;

import com.dg.dg_heritage.Response.Response;
import com.dg.dg_heritage.admin.model.Login;

public interface AdminService {

	Response login(Login request);

	Response createProduct(String payload, String imageMeta, MultipartFile[] images);

	Response updateProduct(Long id, String payload, String imageMeta, MultipartFile[] images);

	Response getCategories();

}
