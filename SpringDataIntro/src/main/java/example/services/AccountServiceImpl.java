package example.services;

import example.models.Account;
import example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Account account = this.accountRepository.findById(Math.toIntExact(id)).get();
        BigDecimal current = account.getBalance();
        account.setBalance(current.subtract(amount));
        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {

    }

    @Override
    public void saveAccount(Account account) {
        this.accountRepository.save(account);
    }
}
