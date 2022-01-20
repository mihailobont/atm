package com.mihailobont.zinkworks.controller;

import com.mihailobont.zinkworks.dto.BalanceRequest;
import com.mihailobont.zinkworks.dto.BalanceResponse;
import com.mihailobont.zinkworks.dto.WithdrawalRequest;
import com.mihailobont.zinkworks.dto.WithdrawalResponse;
import com.mihailobont.zinkworks.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(path = "check")
    public ResponseEntity<BalanceResponse> checkBalance(@RequestBody BalanceRequest balanceRequest){
        return accountService.checkBalance(balanceRequest);
    }

    @PostMapping(path = "withdrawal")
    public ResponseEntity<WithdrawalResponse> withdraw(@RequestBody WithdrawalRequest withdrawalRequest){
        return accountService.withdraw(withdrawalRequest);
    }
}
