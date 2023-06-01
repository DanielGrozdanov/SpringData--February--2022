package example.services;

import example.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal amount, Long id);

    void transferMoney(BigDecimal amount, Long id);

    void saveAccount(Account account);
}
