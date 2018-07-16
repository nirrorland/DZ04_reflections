package ru.raiffeisen.cources.atm;

import ru.raiffeisen.cources.atm.model.constants.CurrencyHolder;
import ru.raiffeisen.cources.atm.model.money.Money;
import ru.raiffeisen.cources.atm.model.score.*;

import java.util.Random;

@OperationalLimit(limit = 20000)
public class ATM implements IATM {
    private CurrentScore currentScore;
    private DebetScore debetScore;
    private CreditScore creditScore;
    public static double AllScoreLimit = 0;

    public ATM() {
        this.creditScore =
                new CreditScore(
                        new Money(100000.0d,
                                  CurrencyHolder.getCurrencyByName("RUR").getName()
                                 ), null, 1);
        this.debetScore =
                new DebetScore(
                        new Money(1000.0d,
                                CurrencyHolder.getCurrencyByName("RUR").getName()
                        ), null, 1, this.creditScore);
        this.currentScore =
                new CurrentScore(
                        new Money(1000.0d,
                                CurrencyHolder.getCurrencyByName("RUR").getName()
                        ), null, 1, this.debetScore);
    }

    public CurrentScore getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(CurrentScore currentScore) {
        this.currentScore = currentScore;
    }

    public Score getDebetScore() {
        return debetScore;
    }

    public void setDebetScore(DebetScore debetScore) {
        this.debetScore = debetScore;
    }

    public Score getCreditScore() {


            Random random = new Random();

            try {
               // Thread.sleep(random.nextInt(3000));
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return creditScore;
    }

    public void setCreditScore(CreditScore creditScore) {
        this.creditScore = creditScore;
    }
}
