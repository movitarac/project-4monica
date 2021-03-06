package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JournalComptableTest {

    private static JournalComptable vJournal;
    private static List<JournalComptable> vJournalList;


    @BeforeEach
    void init(){
        vJournal = new JournalComptable();
        vJournal.setCode("AB");
        vJournal.setLibelle("credit client");

        vJournalList = new ArrayList<>();
        vJournalList.add(vJournal);
        vJournalList.add(new JournalComptable("AC","transport"));

    }
    @Test
    public void getByCodeTest(){
        assertEquals(JournalComptable.getByCode(vJournalList,"AB"),vJournal);
    }

    @Test
    public void toStringTest() {
        String realToString="JournalComptable{code='AB', libelle='credit client'}";
        assertEquals(vJournal.toString(),realToString);



    }
}
