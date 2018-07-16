package ru.raiffeisen.cources.atm;

import ru.raiffeisen.cources.atm.model.money.Money;

import java.lang.reflect.Proxy;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    ATM atmOrigin = new ATM();
        IATM atm =(IATM) Proxy.newProxyInstance(atmOrigin.getClass().getClassLoader(),atmOrigin.getClass().getInterfaces(),new ATMHandler(atmOrigin));

        printScoreInfo(atmOrigin);

        Scanner scanner = new Scanner(System.in);
        int operation = -1;

        while (operation !=0) {
            System.out.println("Выберите операцию:");
            System.out.println("1. Пополнение счёта");
            System.out.println("2. Снятие средств со счёта");
            System.out.println("9. Информация о счетах");
            System.out.println("0. Выход");

            operation = scanner.nextInt();

            int val;
            Money money;

            switch (operation) {


                case 1 : System.out.println("Введите кол-во средств (в рублях)");val = scanner.nextInt();money = new Money(val, "RUR");;
                    atm.getCreditScore().addMoney(money);
                    break;

                case 2 : System.out.println("Введите кол-во средств (в рублях)");val = scanner.nextInt();money = new Money(val, "RUR");
                    atm.getCreditScore().getMoney(val);
                    break;

                case 9 : printScoreInfo(atmOrigin);
                        break;

                case 0 :          break;
            }

        }

        printScoreInfo(atmOrigin);
    }

    private static void printScoreInfo(ATM atmOrigin) {
        System.out.println("CreditScore: " + atmOrigin.getCreditScore().getBalance().getValue());
        System.out.println("DebetScore: " + atmOrigin.getDebetScore().getBalance().getValue());
    }
}
