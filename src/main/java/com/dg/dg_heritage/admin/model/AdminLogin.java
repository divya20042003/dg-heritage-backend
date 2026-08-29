package com.dg.dg_heritage.admin.model;


	import jakarta.persistence.*;
	import java.time.LocalDateTime;

	@Entity
	@Table(name = "dg_admin_login")
	public class AdminLogin {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "admin_id")
	    private Long adminId;

	    @Column(name = "admin_name")
	    private String adminName;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "password")
	    private String password;

	    @Column(name = "is_active")
	    private Boolean isActive;

	    @Column(name = "last_login")
	    private LocalDateTime lastLogin;

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    public Long getAdminId() {
	        return adminId;
	    }

	    public void setAdminId(Long adminId) {
	        this.adminId = adminId;
	    }

	    public String getAdminName() {
	        return adminName;
	    }

	    public void setAdminName(String adminName) {
	        this.adminName = adminName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Boolean getIsActive() {
	        return isActive;
	    }

	    public void setIsActive(Boolean isActive) {
	        this.isActive = isActive;
	    }

	    public LocalDateTime getLastLogin() {
	        return lastLogin;
	    }

	    public void setLastLogin(LocalDateTime lastLogin) {
	        this.lastLogin = lastLogin;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDateTime createdAt) {
	        this.createdAt = createdAt;
	    }

	    public LocalDateTime getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(LocalDateTime updatedAt) {
	        this.updatedAt = updatedAt;
	    }
	}

