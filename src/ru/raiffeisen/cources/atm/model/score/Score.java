package ru.raiffeisen.cources.atm.model.score;

import ru.raiffeisen.cources.atm.ATM;
import ru.raiffeisen.cources.atm.model.account.Account;
import ru.raiffeisen.cources.atm.model.money.Money;
import ru.raiffeisen.cources.atm.model.money.MoneyInterface;

import java.lang.annotation.Annotation;


public abstract class Score implements MoneyInterface {
   private Money balance;
   private Account owner;
   private Integer number;
   private double maxOperation = 0;

    public Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
    }

    public Money getBalance() {
        log();
        return balance;
    }

    public void setBalance(Money balance) {
        log();
        this.balance = balance;
    }

    public Account getOwner() {
        log();
        return owner;
    }

    public void setOwner(Account owner) {
        log();
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (balance != null ? !balance.equals(score.balance) : score.balance != null) return false;
        if (owner != null ? !owner.equals(score.owner) : score.owner != null) return false;
        return number != null ? number.equals(score.number) : score.number == null;
    }

    @Override
    public int hashCode() {
        int result = balance != null ? balance.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Score{" +
                "balance=" + balance +
                ", owner=" + owner +
                ", number=" + number +
                '}';
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void addMoney(Money money){
        log();

       if (!(checkOperationalLimit())) {
            return;
        };

        if (!(checkAllScoreLimit(money.getValue()))) {
            return;
        };


       /* double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCource();

    */
      /*  if(usdValueThis < usdValueIn) {
            System.out.println("You have no so much!");
            return;
        }
        */

        if(checkBefore()) {
          //  this.balance.setValue((usdValueThis + usdValueIn) * this.balance.getCurrency().getUsdCource());
            this.balance.setValue(this.balance.getValue()+ money.getValue());
        } else {
            System.out.println("No check!");
            return;
        }
    }

    private boolean checkAllScoreLimit(double money) {
        Class thisClass = this.getClass();


//        System.out.println("AllScore limit checking");

        for (Annotation annotation : thisClass.getAnnotations()){
            System.out.println(annotation.toString());
            if(annotation instanceof AllScroLimit){

                AllScroLimit allScoreLimit = (AllScroLimit) annotation;

                if((ATM.AllScoreLimit+money) > allScoreLimit.limit()){
                    System.out.println("AllScoreLimit reached. U can't do more operation.");
                    return false;
                }
            }
        }

        ATM.AllScoreLimit = ATM.AllScoreLimit + money;
        return true;

    }

    private boolean checkOperationalLimit() {
        Class thisClass = this.getClass();

        this.maxOperation++;

        System.out.println("CheckOpertationalLimit" + (this.maxOperation));

        for (Annotation annotation : thisClass.getAnnotations()){
            //System.out.println(annotation.toString());
            if(annotation instanceof OperationalLimit){

                OperationalLimit pperationalLimit = (OperationalLimit) annotation;

                if((this.maxOperation) > pperationalLimit.limit()){
                    System.out.println("Operational limit reached. U can't do more operation.");
                    return false;
                }


            }
        }

        return true;
    }

    @Override
    public Money getMoney(double balanceLess){
        log();
        if (!(checkOperationalLimit())) {
            throw new IllegalArgumentException("Wrong!");
        };

        if (!(checkAllScoreLimit(balanceLess))) {
            throw new IllegalArgumentException("Wrong!");
        };

        if(balanceLess > 30000) {
            throw new IllegalArgumentException("Wrong balance less!");
        }

        this.balance.setValue(this.balance.getValue() - balanceLess);

        return this.balance;
    }

    @Override
    public Money getMoneyWithoutLess(){

        log();
        return this.balance;
    }

    abstract boolean checkBefore();

    private void log() {
        boolean isLoggable = false;
      //  System.out.println("loggable");
        Class thisClass = this.getClass();;
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof Loggable) {
                isLoggable = true;
            }
        }

        //System.out.println(isLoggable);
        if(isLoggable) {

            //System.out.println("Executing method " + this.getClass().getEnclosingMethod().getName());

            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement e = stacktrace[2];//coz 0th will be getStackTrace so 1st
            String methodName = e.getMethodName();
            System.out.println("Executing method " + methodName);
        }
    }
}
