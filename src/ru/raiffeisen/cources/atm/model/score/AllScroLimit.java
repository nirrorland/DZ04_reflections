package ru.raiffeisen.cources.atm.model.score;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface AllScroLimit  {
    double limit();
}
