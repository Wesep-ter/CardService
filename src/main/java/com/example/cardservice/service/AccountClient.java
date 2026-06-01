package com.example.cardservice.service;

import com.example.cardservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "account-service", url = "http://localhost:8081/api/account")
public interface AccountClient {

    @GetMapping("/{id}")
    AccountDto getAccountById(@PathVariable("id") Long accountId);
}