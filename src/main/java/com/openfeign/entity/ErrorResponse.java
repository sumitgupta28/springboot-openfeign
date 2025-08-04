package com.openfeign.entity;

public record ErrorResponse(String errorMessage) {

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public static class ErrorResponseBuilder {
        private String errorMessage;

        ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this.errorMessage);
        }

        public String toString() {
            return "ErrorResponse.ErrorResponseBuilder(errorMessage=" + this.errorMessage + ")";
        }
    }
}
