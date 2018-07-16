package ru.raiffeisen.cources.atm.model.score;

import ru.raiffeisen.cources.atm.ATM;
import ru.raiffeisen.cources.atm.model.account.Account;
import ru.raiffeisen.cources.atm.model.money.Money;

@AllScroLimit(limit = 5000)
public abstract class AllScoreLimit extends Score {


    public AllScoreLimit(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }
}
