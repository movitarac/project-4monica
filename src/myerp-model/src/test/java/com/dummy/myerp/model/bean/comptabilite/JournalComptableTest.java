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
        vJournal.setCode("ab1");
        vJournal.setLibelle("house credit");

        vJournalList = new ArrayList<>();
        vJournalList.add(vJournal);
        vJournalList.add(new JournalComptable("ac2","transport"));

    }
    @Test
    public void getByCodeTest(){
        assertEquals(JournalComptable.getByCode(vJournalList,"ab1"),vJournal);
    }


}
