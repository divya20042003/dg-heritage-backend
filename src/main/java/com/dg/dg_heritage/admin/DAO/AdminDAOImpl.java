package com.dg.dg_heritage.admin.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.dg.dg_heritage.Response.Response;
import com.dg.dg_heritage.Response.ResponseHelper;
import com.dg.dg_heritage.admin.model.AdminLogin;
import com.dg.dg_heritage.admin.model.Login;
import com.dg.dg_heritage.util.Constant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class AdminDAOImpl implements AdminDAO{
	   @PersistenceContext
	    private EntityManager entityManager;
	    private ObjectMapper mapper = new ObjectMapper();

	@Override
	public AdminLogin login(Login request) {

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM dg_admin_login " +
                "WHERE email = :email " +
                "AND password = :password " +
                "AND is_active = true",
                AdminLogin.class
        );

        query.setParameter("email", request.getEmail());
        query.setParameter("password", request.getPassword());
        query.setMaxResults(1);

        List<AdminLogin> list = query.getResultList();

        return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@Transactional
	public void updateLastLogin(Long adminId) {
		Query query = entityManager.createNativeQuery(
                "UPDATE dg_admin_login " +
                "SET last_login = now() " +
                "WHERE admin_id = :adminId"
        );

        query.setParameter("adminId", adminId);
        query.executeUpdate();		
	}

	@Override
    @Transactional
    public Response createProduct(String payload, String imageMeta, MultipartFile[] images) {

        try {

            JsonNode root = mapper.readTree(payload);

            Long categoryId = root.get("categoryId").asLong();
            String productName = root.get("productName").asText();
            String productDesc = root.get("productDescription").asText();
            double sellingPrice = root.get("sellingPrice").asDouble();
            double strikePrice = root.get("strikePrice").asDouble();
            int stock = root.get("stockQuantity").asInt();

            // 🔥 1. INSERT PRODUCT
            Query q = entityManager.createNativeQuery(
                    "INSERT INTO dg_product_details " +
                    "(category_id, product_name, product_description, selling_price, strike_price, stock_quantity, is_available) " +
                    "VALUES (:cid, :name, :desc, :sp, :mrp, :stock, true) RETURNING product_id"
            );

            q.setParameter("cid", categoryId);
            q.setParameter("name", productName);
            q.setParameter("desc", productDesc);
            q.setParameter("sp", sellingPrice);
            q.setParameter("mrp", strikePrice);
            q.setParameter("stock", stock);

            Long productId = ((Number) q.getSingleResult()).longValue();

            // 🔥 2. ATTRIBUTES
            JsonNode attrs = root.get("attributes");

            if (attrs != null) {
                for (JsonNode a : attrs) {
                    Query aq = entityManager.createNativeQuery(
                            "INSERT INTO dg_product_attributes (product_id, attribute_name, attribute_value) " +
                            "VALUES (:pid, :name, :value)"
                    );

                    aq.setParameter("pid", productId);
                    aq.setParameter("name", a.get("attributeName").asText());
                    aq.setParameter("value", a.get("attributeValue").asText());
                    aq.executeUpdate();
                }
            }

            // 🔥 3. VARIANTS
            JsonNode variants = root.get("variants");

            if (variants != null) {
                for (JsonNode v : variants) {
                    Query vq = entityManager.createNativeQuery(
                            "INSERT INTO dg_product_variants (product_id, size, color, stock_quantity, sku) " +
                            "VALUES (:pid, :size, :color, :stock, :sku)"
                    );

                    vq.setParameter("pid", productId);
                    vq.setParameter("size", v.get("size").asText());
                    vq.setParameter("color", v.get("color").asText());
                    vq.setParameter("stock", v.get("stockQuantity").asInt());
                    vq.setParameter("sku", v.get("sku").asText());

                    vq.executeUpdate();
                }
            }

            // 🔥 4. IMAGES (placeholder)
            if (images != null) {
                for (MultipartFile file : images) {

                    Query iq = entityManager.createNativeQuery(
                            "INSERT INTO dg_product_images (product_id, image_url, is_primary) " +
                            "VALUES (:pid, :url, false)"
                    );

                    iq.setParameter("pid", productId);
                    iq.setParameter("url", "UPLOAD_LATER_" + file.getOriginalFilename());
                    iq.executeUpdate();
                }
            }

            return ResponseHelper.getSuccessResponse(
                    "Product created successfully",
                    productId,
                    Constant.STATUS_CODE_SUCCESS,
                    "SUCCESS"
            );

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseHelper.getErrorResponse(
                    "Product creation failed",
                    null,
                    Constant.STATUS_CODE_SERVER_ERROR,
                    "PRODUCT_CREATE_FAILED"
            );
        }
    }

	@Override
    @Transactional
    public Response updateProduct(Long id, String payload, String imageMeta, MultipartFile[] images) {

        try {

            Query q = entityManager.createNativeQuery(
                    "UPDATE dg_product_details SET product_name = :name WHERE product_id = :id"
            );

            q.setParameter("name", "UPDATED_PRODUCT");
            q.setParameter("id", id);
            q.executeUpdate();

            return ResponseHelper.getSuccessResponse(
                    "Product updated successfully",
                    id,
                    Constant.STATUS_CODE_SUCCESS,
                    "SUCCESS"
            );

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseHelper.getErrorResponse(
                    "Update failed",
                    null,
                    Constant.STATUS_CODE_SERVER_ERROR,
                    "PRODUCT_UPDATE_FAILED"
            );
        }
    }

    @Override
    public Response getCategories() {

        Query q = entityManager.createNativeQuery(
                "SELECT category_id, category_name FROM dg_product_category"
        );

        List<?> list = q.getResultList();

        return ResponseHelper.getSuccessResponse(
                "Categories fetched",
                list,
                Constant.STATUS_CODE_SUCCESS,
                "SUCCESS"
        );
    }
}