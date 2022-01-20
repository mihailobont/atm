package com.mihailobont.zinkworks.dto;

public class WithdrawalResponse {

    private Long accountNumber;
    private String message;

    public WithdrawalResponse(String message) {
        this.message = message;
    }
}
