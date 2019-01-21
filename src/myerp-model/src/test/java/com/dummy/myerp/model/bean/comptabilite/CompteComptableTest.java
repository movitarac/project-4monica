package com.dummy.myerp.model.bean.comptabilite;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CompteComptableTest {

    private CompteComptable vCompte;
    private List<CompteComptable> compteList;


    @Test
    void getByNumeroTest() {
        vCompte = new CompteComptable();
        vCompte.setNumero(156);
        vCompte.setLibelle("Mr test");
        compteList = new ArrayList<>();
        compteList.add(vCompte);
        compteList.add(new CompteComptable(120,"Client"));
        assertEquals(CompteComptable.getByNumero(compteList,156),vCompte);

    }

    @Test
    void toStringTest() {
        vCompte = new CompteComptable();
        vCompte.setLibelle("test");
        vCompte.setNumero(2);

        String tostringReal = ("CompteComptable{numero=2, libelle='test\'}");
        assertEquals(tostringReal,vCompte.toString());
    }



}
