package com.kodilla.transfersgateway.controller;

import com.kodilla.commons.Transfer;
import com.kodilla.transfersgateway.controller.request.TransferRequest;
import com.kodilla.transfersgateway.service.TransferProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferProducer transferProducer;

    @PostMapping
    public void saveTransfer(@RequestBody TransferRequest request) throws NoFundsInTheAccountException {
        log.info("Received transfer request: {}", request);
        Transfer transfer = new Transfer();
        transfer.setAmount(request.getAmount());
        transfer.setRecipientAccount(request.getRecipientAccount());
        transfer.setSenderAccount(request.getSenderAccount());
        transfer.setTitle(request.getTitle());
        transfer.setAccountBalance(request.getAccountBalance());

        if(transfer.getAccountBalance().compareTo(transfer.getAmount()) >= 0) {
            transferProducer.sendTransfer(transfer);
        } else {
            throw new NoFundsInTheAccountException();
        }

    }
}
