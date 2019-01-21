package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.runners.MockitoJUnitRunner;



import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.dummy.myerp.consumer.ConsumerHelper.getDaoProxy;

//@ExtendWith(MockitoJUnitRunner.class)
public class ComptabiliteManagerImplTest extends BusinessTestCase {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
    EcritureComptable vEcritureComptable = new EcritureComptable();
    Date today = new Date();
    SimpleDateFormat date = new SimpleDateFormat();
    String year;
    @Test
    public void addReferenceTest() throws Exception {
        assertThrows(NotFoundException.class,
                ()->{
                    vEcritureComptable.setJournal(new JournalComptable("BQ", "Banque"));
                    vEcritureComptable.setDate(today);
                    vEcritureComptable.setLibelle("Libelle");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                            null, new BigDecimal(234),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                            null, null,
                            new BigDecimal(234)));
                    manager.addReference(vEcritureComptable);

                });
    }


    @Test
    public void checkEcritureComptableUnit() throws Exception {
        assertThrows(FunctionalException.class,
                ()->{
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(today);
        vEcritureComptable.setLibelle("libelle");
        vEcritureComptable.setReference("AC");
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
    public void checkEcritureComptableUnitViolation() throws Exception {
        assertThrows(FunctionalException.class,
                ()-> {
            vEcritureComptable.setReference("BQ-2016/00001");
            manager.checkEcritureComptableUnit(vEcritureComptable);
             });
    }

    @Test
    public void checkEcritureComptableUnitRG2() throws Exception {
        assertThrows(FunctionalException.class,()->{
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(today);
            vEcritureComptable.setLibelle("Libelle");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(234),
                    null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                    null, null,
                    new BigDecimal(2346)));
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });
    }

    @Test
    public void checkEcritureComptableUnitRG3() throws Exception {
        assertThrows(FunctionalException.class,
                ()->{
                    vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
                    vEcritureComptable.setDate(today);
                    vEcritureComptable.setLibelle("Libelle");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, null,
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, null,
                            null));
                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });

    }

    @Test
    public void checkEcritureComptableUnitRG5() throws Exception {

                vEcritureComptable.setDate(today);
                year = date.format(vEcritureComptable.getDate());
                    vEcritureComptable.setLibelle("Libelle");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(123),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(123),
                            null));

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        assertThrows(FunctionalException.class,
                ()->{
            vEcritureComptable.setReference("AC-" + year + "/00005");
            vEcritureComptable.getJournal().getCode();
                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });
        assertThrows(FunctionalException.class, () -> {
            vEcritureComptable.setReference("AC-" + 2018 + "/00005");
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });

    }

    @Test
    public void checkEcritureComptableUnitRG6() throws Exception {
        assertThrows(FunctionalException.class,
                ()->{

            vEcritureComptable.setReference("BQ-2016/00003");
                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });

    }
    @Test
    public void deleteEcritureComptableTest() {
        vEcritureComptable.setId(-1);
    manager.deleteEcritureComptable(-1);
    }

    @Test
    public void checkEcritureComptableContextTestRG6() {
        assertThrows(FunctionalException.class,
                () -> {

                    vEcritureComptable.setReference("BQ-2016/00003");
                    manager.checkEcritureComptableContext(vEcritureComptable);
                });

    }
    @Test
    public void insertEcritureComptableTest(){
        assertThrows(FunctionalException.class,
                () -> {

                    vEcritureComptable.setJournal(new JournalComptable("BQ", "Banque"));
                    vEcritureComptable.setDate(today);
                    year = date.format(vEcritureComptable.getDate());
                    vEcritureComptable.setLibelle("Libelle");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(24),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                            null, null,
                            new BigDecimal(24)));
                    vEcritureComptable.setId(-7);

                    vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode()+ "-"+year+"/00007");
                    manager.insertEcritureComptable(vEcritureComptable);
                });


    }

    @Test
    public void updateEcritureComptableTest(){


              vEcritureComptable.setId(-8);
                vEcritureComptable.setDate(today);
                vEcritureComptable.setJournal(new JournalComptable("BQ", "Banque"));
                year = date.format(vEcritureComptable.getDate());
                vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode()+ "-"+year+"/00007");
                vEcritureComptable.setLibelle("test insert");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(24),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(24)));

        assertThrows(FunctionalException.class,
                () -> {
            manager.insertEcritureComptable(vEcritureComptable);
               EcritureComptable ecritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptable(-8);
               ecritureComptable.setLibelle("test update");
               manager.updateEcritureComptable(ecritureComptable);
               /*assertEquals("test update", ecritureComptable.getLibelle());*/

        });


    }


}
