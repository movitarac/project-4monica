package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SequenceEcritureComptableTest {

    @Test
    public void toStringTest() {
        SequenceEcritureComptable vSequence = new SequenceEcritureComptable();
        vSequence.setAnnee(2019);
        vSequence.setDerniereValeur(59);
        String realToString="SequenceEcritureComptable{annee=2019, derniereValeur=59}";
        assertEquals(vSequence.toString(),realToString);
    }

    @Test
    public void constructor3ParamsTest(){
        SequenceEcritureComptable vSequence1 = new SequenceEcritureComptable("AC",2019,60);
        SequenceEcritureComptable vSequence2 = new SequenceEcritureComptable("AC",2019,60);


        assertEquals(vSequence1.getAnnee(),vSequence2.getAnnee());

    }
    @Test
    public void constructor2AParamsTest(){
        SequenceEcritureComptable vSequence1 = new SequenceEcritureComptable(2019,60);
        SequenceEcritureComptable vSequence2 = new SequenceEcritureComptable(2019,60);


        assertEquals(vSequence1.getDerniereValeur(),vSequence2.getDerniereValeur());

    }
    @Test
    public void constructor2BParamsTest(){
        SequenceEcritureComptable vSequence1 = new SequenceEcritureComptable("BQ",2019);
        SequenceEcritureComptable vSequence2 = new SequenceEcritureComptable("BQ",2019);


        assertEquals(vSequence1.getJournalCode(),vSequence2.getJournalCode());

    }
}