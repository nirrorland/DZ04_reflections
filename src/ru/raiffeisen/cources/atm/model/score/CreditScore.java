package ru.raiffeisen.cources.atm.model.score;

import ru.raiffeisen.cources.atm.model.account.Account;
import ru.raiffeisen.cources.atm.model.money.Money;

@Loggable
@OperationalLimit(limit = 2)
public class CreditScore extends AllScoreLimit {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    public void addMoney(Money money){
        super.addMoney(money);
    }

    @Override
    public Money getMoney(double balanceLess){
        return super.getMoney(balanceLess);
    }

    @Override
    public Money getMoneyWithoutLess(){
        return super.getMoneyWithoutLess();
    }

    @Override
    boolean checkBefore() {
        return true;
    }
}
