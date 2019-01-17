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
}