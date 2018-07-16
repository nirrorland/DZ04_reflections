package ru.raiffeisen.cources.atm;

import ru.raiffeisen.cources.atm.model.score.CreditScore;
import ru.raiffeisen.cources.atm.model.score.Score;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ATMHandler implements InvocationHandler{
    private IATM atm;

    public ATMHandler(IATM atm) {
        this.atm = atm;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        long waitedTime = 0;

        Object result = method.invoke(atm, args);

        waitedTime = System.currentTimeMillis() - startTime;

        System.out.println("Waited time: " + waitedTime);

        if (waitedTime > 10) {
            for (Method me : this.atm.getClass().getDeclaredMethods()){
               // System.out.println((me.getName()));
                if (me.getName().equals(method.getName())) {
                  //do nothing
                } else {
                    if ((me.getName().contains("get"))&&(method.getName().contains("get"))) {
                      Object result2 = me.invoke(atm, args);
                      return  result2;
                    };

                    if ((me.getName().contains("set"))&&(method.getName().contains("set"))) {
                       Object result3 = me.invoke(atm, args);
                       return result3;
                    };

                }

            }
            System.out.println(method.getName());
        }

        return result;
    }
}
