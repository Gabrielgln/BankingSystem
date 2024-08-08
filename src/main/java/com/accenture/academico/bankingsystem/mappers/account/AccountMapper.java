package com.accenture.academico.bankingsystem.mappers.account;

import com.accenture.academico.bankingsystem.domain.account.Account;
import com.accenture.academico.bankingsystem.dtos.account.AccountResponseDTO;
import com.accenture.academico.bankingsystem.dtos.account.AccountRequestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public static Account convertToAccount(AccountRequestDTO accountDTO) {
        Account account = new Account();
        account.setNumber(accountDTO.number());
        account.setAccountType(accountDTO.accountType());
        account.setAgency(account.getAgency());
        account.setClient(account.getClient());
        account.setBalance(BigDecimal.ONE);
        return account;
    }

    public static AccountResponseDTO convertToAccountResponseDTO(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getNumber(),
                account.getAccountType(),
                account.getAgency().getId(),
                account.getBalance(),
                account.getClient().getId()
        );
    }

    public static List<AccountResponseDTO> convertToAccountResponseDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::convertToAccountResponseDTO)
                .collect(Collectors.toList());
    }
}
