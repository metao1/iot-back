package com.iot.model;

import org.springframework.lang.NonNull;

public class ErrorResponse {

	private String code;
	private String message;
	private String description;
	private String errorCode;

	private ErrorResponse(final Builder builder) {
		this.code = builder.code;
		this.message = builder.message;
		this.description = builder.description;
		this.errorCode = builder.errorCode;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	public String getErrorCode() {
		return errorCode;
	}

	@NonNull
	public static Builder newBuilder() {
		return new Builder();
	}
	public static final class Builder {
		private String code;
		private String message;
		private String description;

		private String errorCode;

		private Builder() {
		}

		@NonNull
		public Builder withCode(String code) {
			this.code = code;
			return this;
		}

		@NonNull
		public Builder withMessage(final String message) {
			this.message = message;
			return this;
		}

		@NonNull
		public Builder withDescription(final String description) {
			this.description =  description == null ? "" : description.replaceAll("\"", "'");
			return this;
		}

		@NonNull
		public Builder withErrorCode(final String errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		@NonNull
		public ErrorResponse build() {
			return new ErrorResponse(this);
		}
	}
}
