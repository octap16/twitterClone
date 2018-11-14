package com.example.twitterClone.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties (ignoreUnknown = true)
public class CaptchaResponseDto {
    private boolean succes;
    @JsonAlias("error-codes")
    private Set<String> errorCode;

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public Set<String> getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Set<String> errorCode) {
        this.errorCode = errorCode;
    }
}
