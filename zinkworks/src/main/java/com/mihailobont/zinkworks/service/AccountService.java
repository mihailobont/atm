package com.mihailobont.zinkworks.service;

import com.mihailobont.zinkworks.dto.*;
import com.mihailobont.zinkworks.modal.Account;
import com.mihailobont.zinkworks.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AtmService atmService;

    @PostConstruct
    private void init() {
        accountRepository.save(new Account(123456789L, 1234, 800L, 200L));
        accountRepository.save(new Account(987654321L, 4321, 1230L, 150L));
    }

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

                    int withdrawDone = updateAccountForWithdraw(accountFound.getId(),
                            accountFound.getOpeningBalance() - withdrawalRequest.getAmountToWithdraw(),
                            accountFound.getOverdraft() + withdrawalRequest.getAmountToWithdraw());

                    if (withdrawDone == 1) {
                        return atmService.withdraw(withdrawalRequest.getId(), withdrawalRequest.getAccountNumber(),
                                withdrawalRequest.getAmountToWithdraw());
                    } else {

                        String message = "The withdraw was not successful.";

                        WithdrawalResponse withdrawalResponse = new WithdrawalResponse(message);

                        return ResponseEntity.status(HttpStatus.OK).body(withdrawalResponse);
                    }

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

    private int updateAccountForWithdraw(Long id, Long openingBalance, Long overdraft) {
        return accountRepository.updateAccountForWithdraw(id, openingBalance, overdraft);
    }

    public ResponseEntity<AddAccountResponse> save(AddAccountRequest addAccountRequest) {

        boolean accountExists = accountRepository.findByAccountNumber(addAccountRequest.getAccountNumber());

        if (accountExists) {
            return ResponseEntity.status(HttpStatus.OK).body(new AddAccountResponse(new Account(), "Account is already existing."));
        }

        Account accountSaved = accountRepository.save(new Account(
                addAccountRequest.getAccountNumber(),
                addAccountRequest.getPin(),
                addAccountRequest.getOpeningBalance(),
                addAccountRequest.getOverdraft()
        ));

        return ResponseEntity.status(HttpStatus.OK).body(new AddAccountResponse(accountSaved, "Account added successfully."));
    }
}
