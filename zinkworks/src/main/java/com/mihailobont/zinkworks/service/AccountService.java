package com.mihailobont.zinkworks.service;

import com.mihailobont.zinkworks.dto.BalanceRequest;
import com.mihailobont.zinkworks.dto.BalanceResponse;
import com.mihailobont.zinkworks.dto.WithdrawalRequest;
import com.mihailobont.zinkworks.dto.WithdrawalResponse;
import com.mihailobont.zinkworks.modal.Account;
import com.mihailobont.zinkworks.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AtmService atmService;

    public ResponseEntity<BalanceResponse> checkBalance(BalanceRequest balanceRequest) {

        Optional<Account> account = accountRepository.findById(balanceRequest.getId());

        if (account.isPresent()) {

            Account accountFound = account.get();

            if (balanceRequest.getPin() == accountFound.getPin()) {

                String message = String.format("The account balance for account %d is %d", accountFound.getAccountNumber(), accountFound.getOpeningBalance());

                BalanceResponse balanceResponse = new BalanceResponse(
                        accountFound.getAccountNumber(),
                        accountFound.getOpeningBalance(),
                        message
                );
                return ResponseEntity.status(HttpStatus.OK).body(balanceResponse);

            } else {
                String message = String.format("The account %d does not exists or the PIN for account %d is not valid.", balanceRequest.getAccountNumber(), balanceRequest.getAccountNumber());

                BalanceResponse balanceResponse = new BalanceResponse(
                        balanceRequest.getAccountNumber(),
                        message
                );

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(balanceResponse);
            }
        } else {
            String message = String.format("The account %d does not exists.", balanceRequest.getAccountNumber());

            BalanceResponse balanceResponse = new BalanceResponse(
                    balanceRequest.getAccountNumber(),
                    message
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(balanceResponse);
        }
    }

    public ResponseEntity<WithdrawalResponse> withdraw(WithdrawalRequest withdrawalRequest) {

        Optional<Account> account = accountRepository.findById(withdrawalRequest.getId());

        if (account.isPresent()) {

            Account accountFound = account.get();

            if (withdrawalRequest.getPin() == accountFound.getPin()) {

                if (withdrawalRequest.getAmountToWithdraw() < accountFound.getOpeningBalance()) {
                    return atmService.withdraw(withdrawalRequest.getId(), withdrawalRequest.getAccountNumber(),withdrawalRequest.getAmountToWithdraw());
                } else {

                    String message = "The amount that you selected exceeds available balance.";

                    WithdrawalResponse withdrawalResponse = new WithdrawalResponse(message);

                    return ResponseEntity.status(HttpStatus.OK).body(withdrawalResponse);
                }
            } else {
                String message = String.format("The account %d does not exists or the PIN for account %d is not valid.", withdrawalRequest.getAccountNumber(), withdrawalRequest.getAccountNumber());

                WithdrawalResponse withdrawalResponse = new WithdrawalResponse(message);

                return ResponseEntity.status(HttpStatus.OK).body(withdrawalResponse);

            }
        } else {
            String message = String.format("The account %d does not exists.", withdrawalRequest.getAccountNumber());

            WithdrawalResponse withdrawalResponse = new WithdrawalResponse(message);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(withdrawalResponse);
        }
    }
}
