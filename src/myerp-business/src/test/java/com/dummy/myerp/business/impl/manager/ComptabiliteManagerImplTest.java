package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;


import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;


import org.junit.jupiter.api.Test;


import static com.dummy.myerp.consumer.ConsumerHelper.getDaoProxy;
import static org.junit.jupiter.api.Assertions.*;


public class ComptabiliteManagerImplTest extends BusinessTestCase {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
    EcritureComptable vEcritureComptable = new EcritureComptable();
    SequenceEcritureComptable vSeq = new SequenceEcritureComptable();
    Date today = new Date();
    SimpleDateFormat date = new SimpleDateFormat("yyyy");
    String year;

    @Test
    public void addReferenceTest() throws NotFoundException {
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2019/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
                null, null,
                new BigDecimal(123)));

        vSeq.setAnnee(2019);
        assertThrows(NotFoundException.class,
                () -> {

                    manager.addReference(vEcritureComptable);
               });
        assertNotNull(vEcritureComptable.getReference());
        assertEquals("AC-2019/00001", vEcritureComptable.getReference());

    }


    @Test
    public void checkEcritureComptableUnit() throws Exception {
        assertThrows(FunctionalException.class,
                () -> {
                    vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
                    vEcritureComptable.setDate(today);
                    vEcritureComptable.setLibelle("libelle");
                    vEcritureComptable.setReference("AC-2019/00001");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(400),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                            null, null,
                            new BigDecimal(400)));
                    manager.checkEcritureComptableUnit(vEcritureComptable);



                });
    }

    @Test
    public void checkEcritureComptableTest() throws Exception {

        assertThrows(FunctionalException.class,
                () -> {
                    vEcritureComptable.setJournal(new JournalComptable("BQ", "Banque"));
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    vEcritureComptable.setDate(dateformat.parse("2016-12-27 10:00:00"));
                    vEcritureComptable.setId(-5);
                    vEcritureComptable.setLibelle("Paiement Facture C110002");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(3000),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                            null, null,
                            new BigDecimal(3000)));
                    vEcritureComptable.setReference("BQ-2016/00003");
                    manager.checkEcritureComptable(vEcritureComptable);
                });

    }

    @Test
    public void checkEcritureComptableUnitViolationTest() throws Exception {
        assertThrows(FunctionalException.class,
                () -> {
                    vEcritureComptable.setReference("BQ-2016/00001");
                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });
    }



    @Test
    public void checkEcritureComptableUnitRG2() throws Exception {
        assertThrows(FunctionalException.class, () -> {
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(today);
            vEcritureComptable.setLibelle("Libelle");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(234),
                    null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, null,
                    new BigDecimal(2346)));
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });
    }

   /* @Test
    public void checkEcritureComptableUnitRG32Credit() throws Exception {

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(today);
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(1234)));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(1234)));
        assertThrows(FunctionalException.class,
                () -> {
                    manager.checkEcritureComptableUnit(vEcritureComptable);

                });
    }*/
    @Test
    public void checkEcritureComptableUnitRG32Debit() {

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(today);
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null,
                null,null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null,
                null,null));
        assertThrows(FunctionalException.class,
                () -> {
                    manager.checkEcritureComptableUnit(vEcritureComptable);

                });
    }

    @Test
    public void checkEcritureComptableUnitRG5() throws Exception {
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(today);
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null, new BigDecimal(123)));
        assertThrows(FunctionalException.class,
                () -> {
                    vEcritureComptable.setReference("AC-2019/00005");
                    assertEquals("AC", vEcritureComptable.getReference().substring(0, 2));
                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });
        DateFormat df = new SimpleDateFormat("yyyy");
        // Get the date today using Calendar object.
        year = df.format(vEcritureComptable.getDate());

        assertThrows(FunctionalException.class,
                () -> {
                    vEcritureComptable.setReference("AC-2019/00005");
                    assertEquals(year, vEcritureComptable.getReference().substring(3, 7));

                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });
    }

    @Test
    public void deleteEcritureComptableTest() {

        vEcritureComptable.setId(-1);
        manager.deleteEcritureComptable(-1);
    }


    @Test
    public void checkEcritureComptableContext() throws FunctionalException {
        vEcritureComptable.setReference("BQ-2016/00007");
        manager.checkEcritureComptableContext(vEcritureComptable);
    }


    @Test
    public void insertEcritureComptableTest() {

        vEcritureComptable.setJournal(new JournalComptable("OD", "Operations"));

        vEcritureComptable.setDate(today);
        vEcritureComptable.setId(-6);
        year = date.format(vEcritureComptable.getDate());
        vEcritureComptable.setLibelle("Libelle to insert");

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                "t1", new BigDecimal(24),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
                "t2", null,
                new BigDecimal(24)));
        vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode() + "-" + year + "/00006");
        assertThrows(FunctionalException.class,
                () -> {
                    manager.insertEcritureComptable(vEcritureComptable);
                    assertNotNull(vEcritureComptable.getId());
                    assertEquals("OD-2019/00006",vEcritureComptable.getReference());
                });
    }


    @Test
    public void updateEcritureComptableTest() throws Exception {
        try {
            List<EcritureComptable> vListeEcritureComptable = manager.getListEcritureComptable();
            for (EcritureComptable vEcritureComptable : vListeEcritureComptable) {
                if (vEcritureComptable.getId() == -5) {
                    vEcritureComptable.setLibelle("test for update");
                    manager.updateEcritureComptable(vEcritureComptable);
                }
            }
        } catch (Exception e) {
            fail("Error during updating");
        }

    }

    @Test
    public void getListJournalComptableTest() {
        List<JournalComptable> vList = manager.getListJournalComptable();
        assertTrue(vList.size() > 1);
    }

    @Test
    public void getListCompteComptableTest() {
        List<CompteComptable> vList = manager.getListCompteComptable();
        assertTrue(vList.size() > 1);
    }


    @Test
    public void insertSequenceEcritureComptableTest() throws NotFoundException {
        vSeq.setDerniereValeur(32);
        vSeq.setAnnee(2017);
        manager.insertSequenceEcritureComptable(vSeq, "OD");
        SequenceEcritureComptable seq =
                getDaoProxy().getComptabiliteDao().getSequenceEcritureComptable("OD", 2017);
        assertEquals(2017, (int) seq.getAnnee());
        getDaoProxy().getComptabiliteDao().deleteSequenceEcritureComptable(seq, "OD");
    }

    @Test
    public void updateSequenceEcritureComptableTest() throws NotFoundException {

        vSeq.setDerniereValeur(32);
        vSeq.setAnnee(2017);
        manager.insertSequenceEcritureComptable(vSeq, "OD");
        SequenceEcritureComptable seq =
                getDaoProxy().getComptabiliteDao().getSequenceEcritureComptable("OD", 2017);
        seq.setDerniereValeur(65);
        manager.updateSequenceEcritureComptable(seq);
        assertEquals(65, (int) seq.getDerniereValeur());
        getDaoProxy().getComptabiliteDao().deleteSequenceEcritureComptable(seq, "OD");
    }





}
