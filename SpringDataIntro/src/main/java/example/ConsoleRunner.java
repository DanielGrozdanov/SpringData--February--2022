package example;

import example.models.Account;
import example.models.User;
import example.services.AccountService;
import example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user = new User("Daniel", 27);
        Account account = new Account(BigDecimal.valueOf(50000), user);
        user.setAccounts(Set.of(account));
        userService.registerUser(user);
        accountService.saveAccount(account);

        accountService.withdrawMoney(BigDecimal.valueOf(20000), (long) account.getId());
    }
}
