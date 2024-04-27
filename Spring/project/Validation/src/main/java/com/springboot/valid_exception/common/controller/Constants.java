package com.springboot.valid_exception.common.controller;

import lombok.Getter;

public class Constants {

    @Getter
    public enum ExceptionClass {
        PRODUCT("Product");

        private final String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + "Exception";
        }

    }
}
