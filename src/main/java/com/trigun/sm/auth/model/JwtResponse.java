package com.trigun.sm.auth.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
    private String userId;
    private String userName;
    private String roles;
    
    
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	
	public JwtResponse(String jwttoken, String userId, String userName, String roles) {
		super();
		this.jwttoken = jwttoken;
		this.userId = userId;
		this.userName = userName;
		this.roles = roles;
	}


	public String getToken() {
		return this.jwttoken;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}