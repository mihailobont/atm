package com.mihailobont.zinkworks.service;

import com.mihailobont.zinkworks.dto.WithdrawalResponse;
import com.mihailobont.zinkworks.modal.Atm;
import com.mihailobont.zinkworks.repository.AtmRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AtmService {

    private final int FIFTY_NOTES = 0;
    private final int TWENTY_NOTES = 1;
    private final int TEN_NOTES = 2;
    private final int FIVE_NOTES = 3;

    private final AtmRepository atmRepository;

    public ResponseEntity<WithdrawalResponse> withdraw(Long atmId, Long accountNumber, Long amountToWithdraw) {


        Long[] notesAvailable = returnNumberOfNotesAvailable(atmId);

        Long sumOfMoneyAvailable = 50L * notesAvailable[FIFTY_NOTES] + 20L * notesAvailable[TWENTY_NOTES] +
                10L * notesAvailable[TEN_NOTES] + 5L * notesAvailable[FIVE_NOTES];

        // TODO: ADD FUNCTIONALITY FOR CHECKING THE AMOUNT OF THE ACCOUNT
        if (sumOfMoneyAvailable < amountToWithdraw){
            String message = "Sorry, the ATM does not have that much money.";
            WithdrawalResponse withdrawalResponse = new WithdrawalResponse(message);
            return ResponseEntity.status(HttpStatus.OK).body(withdrawalResponse);
        }

        Long[] notesToWithdraw = countNotes(atmId, amountToWithdraw);

        boolean withdrawSuccess = atmRepository.withdraw(atmId,
                notesAvailable[FIFTY_NOTES] - notesToWithdraw[FIFTY_NOTES],
                notesAvailable[TWENTY_NOTES] - notesToWithdraw[TWENTY_NOTES],
                notesAvailable[TEN_NOTES] - notesToWithdraw[TEN_NOTES],
                notesAvailable[FIVE_NOTES] - notesToWithdraw[FIVE_NOTES]);



        if(withdrawSuccess){
            String message = String.format("Withdraw done with success! \n" + "The value of the withdraw from the " +
                    "account: %d is: %d.", accountNumber, amountToWithdraw);

            WithdrawalResponse withdrawalResponse = new WithdrawalResponse(accountNumber, amountToWithdraw,
                    notesToWithdraw, message);

            return ResponseEntity.status(HttpStatus.OK).body(withdrawalResponse);

        }else{
            String message = "The withdraw was not successful, please try again.";
            WithdrawalResponse withdrawalResponse = new WithdrawalResponse(message);
            return ResponseEntity.status(HttpStatus.OK).body(withdrawalResponse);
        }

    }

    private Long[] countNotes(Long atmId, Long amountToWithdraw) {

        Double amountToWithdrawAsDouble = amountToWithdraw.doubleValue();
        Long[] notesToWithdraw = new Long[4];
        Long[] notesAvailable = returnNumberOfNotesAvailable(atmId);

        while (((amountToWithdrawAsDouble / 50.0) > 1.0) && (notesAvailable[FIFTY_NOTES] > 0)) {
            notesToWithdraw[FIFTY_NOTES]++;
            amountToWithdrawAsDouble = amountToWithdrawAsDouble - 50.0;
        }

        while (((amountToWithdrawAsDouble / 20.0) > 1.0) && (notesAvailable[TWENTY_NOTES] > 0)) {
            notesToWithdraw[TWENTY_NOTES]++;
            amountToWithdrawAsDouble = amountToWithdrawAsDouble - 20.0;
        }

        while (((amountToWithdrawAsDouble / 10.0) > 1.0) && (notesAvailable[TEN_NOTES] > 0)) {
            notesToWithdraw[TEN_NOTES]++;
            amountToWithdrawAsDouble = amountToWithdrawAsDouble - 10.0;
        }

        while (((amountToWithdrawAsDouble / 5.0) > 1.0) && (notesAvailable[FIVE_NOTES] > 0)) {
            notesToWithdraw[FIVE_NOTES]++;
            amountToWithdrawAsDouble = amountToWithdrawAsDouble - 5.0;
        }

        return notesToWithdraw;

    }

    private Long[] returnNumberOfNotesAvailable(Long atmId) {
        Optional<Atm> atm = atmRepository.findById(atmId);
        Atm atmFound = atm.get();
        Long[] numberOfNotes = {atmFound.getFiftyEurosBills(), atmFound.getTwentyEurosBills(), atmFound.getTenEurosBills(), atmFound.getFiveEurosBills()};
        return numberOfNotes;
    }
}
