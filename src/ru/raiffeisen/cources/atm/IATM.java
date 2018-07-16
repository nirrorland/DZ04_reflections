package ru.raiffeisen.cources.atm;

import ru.raiffeisen.cources.atm.model.score.CreditScore;
import ru.raiffeisen.cources.atm.model.score.CurrentScore;
import ru.raiffeisen.cources.atm.model.score.DebetScore;
import ru.raiffeisen.cources.atm.model.score.Score;

public interface IATM {

    public CurrentScore getCurrentScore();



    public Score getDebetScore() ;





    public Score getCreditScore() ;



}
