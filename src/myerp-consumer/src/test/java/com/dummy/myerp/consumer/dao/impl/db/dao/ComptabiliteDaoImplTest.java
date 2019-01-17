package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        EcritureComptable vEcriture = null;
    }
/***********GET***********/
    @Test
    void getListCompteComptable() {
        List<CompteComptable> listCompteComptable= dao.getListCompteComptable();
       assertEquals(listCompteComptable.size(),7);
    }

    @Test
    void getListJournalComptable() {
        List<JournalComptable> journalComptableList= dao.getListJournalComptable();
        assertEquals(journalComptableList.size(),4);
    }

    @Test
    void getListEcritureComptable() {
        List<EcritureComptable> ecritureComptableList = dao.getListEcritureComptable();
        assertEquals(ecritureComptableList.size(),5);
    }

    @Test
    void getEcritureComptable()  throws NotFoundException {
       vEcriture = dao.getEcritureComptable(-1);
       //assertEquals(vEcriture.);
    }

    @Test
    void getEcritureComptableByRef() throws NotFoundException {
       vEcriture = dao.getEcritureComptableByRef("AC-2016/00001");
        assertEquals("AC", vEcriture.getJournal().getCode());
        //
        assertEquals(2016,2016);
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

      dao.insertEcritureComptable(vEcriture);
    }

    @Test
    void updateEcritureComptable() {
        dao.updateEcritureComptable(vEcriture);
    }

    @Test
    void deleteEcritureComptable() {
        dao.deleteEcritureComptable(3);
    }



    /***********Sequence Ecriture ***********/
    @Test
    void getSequenceEcritureComptable() throws NotFoundException{
        SequenceEcritureComptable toBeFoundSequence = new SequenceEcritureComptable();
        toBeFoundSequence.setJournalCode("VE");
        toBeFoundSequence.setAnnee(2016);

       SequenceEcritureComptable realSequence = dao.getSequenceEcritureComptable(toBeFoundSequence.getJournalCode(),toBeFoundSequence.getAnnee());
      if (realSequence != null){
        assertEquals("VE",realSequence.getJournalCode());
        assertEquals(2016,realSequence.getAnnee());
        assertEquals(41,realSequence.getDerniereValeur());
        } else{
          fail("SequenceEcriture not found or null");
      }

    }

    @Test
    void updateSequenceEcritureComptable() {
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
        sequenceEcritureComptable.setAnnee(2018);
        sequenceEcritureComptable.setDerniereValeur(90);
        sequenceEcritureComptable.setJournalCode("BQ");
        dao.updateSequenceEcritureComptable(sequenceEcritureComptable);
    }
}