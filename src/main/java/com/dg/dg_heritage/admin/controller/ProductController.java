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
@RequestMapping("/admin/products")
@CrossOrigin
public class ProductController {
	@Autowired
	private AdminService adminService;

	@PostMapping(consumes = "multipart/form-data", produces = "application/json")
	public Response createProduct(
			@RequestPart("payload") String payload,
			@RequestPart(value = "imageMeta", required = false) String imageMeta,
			@RequestPart(value = "images", required = false) MultipartFile[] images) {

		return adminService.createProduct(payload, imageMeta, images);
	}

	@PutMapping(value = "/{id}", consumes = "multipart/form-data")
	public Response updateProduct(
			@PathVariable Long id,
			@RequestPart("payload") String payload,
			@RequestPart(value = "imageMeta", required = false) String imageMeta,
			@RequestPart(value = "images", required = false) MultipartFile[] images) {

		return adminService.updateProduct(id, payload, imageMeta, images);
	}

	@GetMapping("/product-categories")
	public Response getCategories() {
		return adminService.getCategories();
	}
}


