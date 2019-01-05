package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CompteComptableTest {

    private CompteComptable vCompte;
    private List<CompteComptable> compteList;

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    @Test
    public void getByNumero() {
        vCompte = new CompteComptable();
        vCompte.setNumero(156);
        vCompte.setLibelle("Mr test");
        compteList = new ArrayList<>();
        compteList.add(vCompte);
        compteList.add(new CompteComptable(120,"Mrs.Test"));
        Assert.assertEquals(CompteComptable.getByNumero(compteList,156),vCompte);


    }



}
