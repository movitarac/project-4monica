package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


class ComptabiliteDaoImplTest extends ConsumerTestCase {

   private ComptabiliteDao dao = getDaoProxy().getComptabiliteDao();
   private EcritureComptable vEcriture;

    @BeforeEach
    void setUp() {
        vEcriture = new EcritureComptable();
    }

    @AfterEach
    void tearDown() {
        vEcriture = null;
    }
/***********GET***********/
    @Test
    void getListCompteComptable() {
        List<CompteComptable> listCompteComptable= dao.getListCompteComptable();
      // assertEquals(listCompteComptable.size(),7);
        assertTrue(listCompteComptable.size()>1);
    }

    @Test
    void getListJournalComptable() {
        List<JournalComptable> journalComptableList= dao.getListJournalComptable();
        //assertEquals(4,journalComptableList.size());
        assertTrue(journalComptableList.size()>1);
    }

    @Test
    void getListEcritureComptable() {
        List<EcritureComptable> ecritureComptableList = dao.getListEcritureComptable();
       // assertEquals(5,ecritureComptableList.size());
        assertTrue(ecritureComptableList.size()>1);
    }

    @Test
    public void getEcritureComptable()  throws NotFoundException {
       vEcriture = dao.getEcritureComptable(-2);
       assertNotNull(vEcriture.getJournal().getCode());
    }

    @Test
    void getEcritureComptableByRef()  {
        try {
            vEcriture = dao.getEcritureComptableByRef("BQ-2016/00003");
            assertEquals("BQ", vEcriture.getJournal().getCode());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }




    }
    /***********Ligne Ecriture ***********/
    @Test
    void loadListLigneEcriture() {
    vEcriture.setId(-2);
    dao.loadListLigneEcriture(vEcriture);
    }


    /***********Ecriture Comptable ***********/
    @Test
    void insertEcritureComptable() {
      JournalComptable journalComptable = new JournalComptable();
      journalComptable.setCode("BQ");
      journalComptable.setLibelle("Banque");
      Date vDate = new Date();
      vEcriture.setLibelle("Paiement Facture ABC123");
      vEcriture.setJournal(journalComptable);
      vEcriture.setDate(vDate);
      vEcriture.setId(-6);
      vEcriture.setReference("BQ-2019/00006");
      SimpleDateFormat formatDate = new SimpleDateFormat();
      String year = formatDate.format(vEcriture.getDate());

      dao.insertEcritureComptable(vEcriture);
      dao.deleteEcritureComptable(vEcriture.getId());
    }

    @Test
    void updateEcritureComptable() {
        try {
            vEcriture = dao.getEcritureComptable(-2);
            vEcriture.setLibelle("libelle update from java test class");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        dao.updateEcritureComptable(vEcriture);
    }

    @Test
    void deleteEcritureComptableTest() {
        dao.deleteEcritureComptable(3);
    }



    /***********Sequence Ecriture ***********/
    @Test
    void getSequenceEcritureComptableTest() throws NotFoundException{
        SequenceEcritureComptable toBeFoundSequence = new SequenceEcritureComptable();
        toBeFoundSequence.setJournalCode("VE");
        toBeFoundSequence.setAnnee(2016);

            SequenceEcritureComptable realSequence = dao.getSequenceEcritureComptable(toBeFoundSequence.getJournalCode(), toBeFoundSequence.getAnnee());
            assertNotNull(realSequence);


    }

    @Test
    void updateSequenceEcritureComptableTest() {
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
        sequenceEcritureComptable.setAnnee(2018);
        sequenceEcritureComptable.setDerniereValeur(90);
        sequenceEcritureComptable.setJournalCode("BQ");
        dao.updateSequenceEcritureComptable(sequenceEcritureComptable);
    }

    @Test
    void insertSequenceEcritureComptableTest() throws NotFoundException {
        SequenceEcritureComptable seq = new SequenceEcritureComptable();
        seq.setDerniereValeur(53);
        seq.setAnnee(2019);
        dao.insertSequenceEcritureComptable(seq, "AC");
        SequenceEcritureComptable seqBis = dao.getSequenceEcritureComptable("AC", 2019);
        assertEquals(53, (int) seqBis.getDerniereValeur());


    }
}