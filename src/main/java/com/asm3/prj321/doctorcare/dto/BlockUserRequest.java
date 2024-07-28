package com.asm3.prj321.doctorcare.dto;

public class BlockUserRequest {
	private int userId;
	private boolean isActive;
	private String reasonForBlock;
	
	public String getReasonForBlock() {
		return reasonForBlock;
	}

	public void setReasonForBlock(String reasonForBlock) {
		this.reasonForBlock = reasonForBlock;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public BlockUserRequest() {}
}
