package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LigneEcritureComptableTest {


    @Test
    public void toStringTest() {

        LigneEcritureComptable vLigne = new LigneEcritureComptable();
        vLigne.setCredit(BigDecimal.valueOf(50));
        vLigne.setDebit(null);
        vLigne.setCompteComptable(null);
        vLigne.setLibelle("banque");
        String realToString="LigneEcritureComptable{compteComptable=null, libelle='banque', debit=null, credit=50}";
        assertEquals(vLigne.toString(),realToString);
    }
}