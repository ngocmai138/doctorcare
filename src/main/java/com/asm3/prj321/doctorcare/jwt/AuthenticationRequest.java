package com.asm3.prj321.doctorcare.jwt;

public class AuthenticationRequest {
	private String email;
	private String password;
	private String grantType;
	

	public String getGrantType() {
		return grantType;
	}


	public void setGrantType(String grantType) {
		this.grantType = grantType;
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


	public AuthenticationRequest() {
	}

}
