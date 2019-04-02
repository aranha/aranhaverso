package br.com.gastronomia.dto;


public class StandardResponseDTO {
	private boolean success;
	private String message;

	public StandardResponseDTO(boolean s, String m) {
		success = s;
		message = m;
	}

	public StandardResponseDTO() {}

	public void setMessage(String m) {
		message = m;
	}

	public void setSuccess(boolean s) {
		success = s;
	}

	public String getMessage() {
		return message;
	}

	public boolean getSuccess() {
		return success;
	}
}