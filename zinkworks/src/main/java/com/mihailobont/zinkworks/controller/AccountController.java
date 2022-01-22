package com.mihailobont.zinkworks.controller;

import com.mihailobont.zinkworks.dto.*;
import com.mihailobont.zinkworks.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(path = "/check")
    public ResponseEntity<BalanceResponse> checkBalance(@RequestBody BalanceRequest balanceRequest){
        return accountService.checkBalance(balanceRequest);
    }

    @PutMapping(path = "/withdraw")
    public ResponseEntity<WithdrawalResponse> withdraw(@RequestBody WithdrawalRequest withdrawalRequest){
        return accountService.withdraw(withdrawalRequest);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<AddAccountResponse> save(@RequestBody AddAccountRequest addAccountRequest){
        return accountService.save(addAccountRequest);
    }


}
