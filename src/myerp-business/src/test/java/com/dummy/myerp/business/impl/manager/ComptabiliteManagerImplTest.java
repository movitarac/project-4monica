package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@ExtendWith(MockitoJUnitRunner.class)
public class ComptabiliteManagerImplTest extends BusinessTestCase {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
    EcritureComptable vEcritureComptable = new EcritureComptable();



    @Test
    public void addReferenceTest() throws Exception {
        assertThrows(NotFoundException.class,
                ()->{

                    EcritureComptable vEcritureComptable = new EcritureComptable();
                    vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
                    vEcritureComptable.setDate(new Date());
                    vEcritureComptable.setLibelle("Libelle");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                            null, new BigDecimal(123),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                            null, null,
                            new BigDecimal(123)));
                    manager.addReference(vEcritureComptable);

                });
    }


    @Test
    public void checkEcritureComptableUnit() throws Exception {
        assertThrows(FunctionalException.class,
                ()->{
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
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

    /*@Test
    public void checkEcritureComptableUnitViolation() throws Exception {
        assertThrows(FunctionalException.class,
                ()-> manager.checkEcritureComptableUnit(vEcritureComptable);
             });
    }*/

    @Test
    public void checkEcritureComptableUnitRG2() throws Exception {
        assertThrows(FunctionalException.class,
                ()->{ vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
                });
    }

    @Test
    public void checkEcritureComptableUnitRG3() throws Exception {
        assertThrows(FunctionalException.class,
                ()->{
                    vEcritureComptable.setLibelle("Libelle");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(123),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(123),
                            null));
                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });

    }

    @Test
    public void checkEcritureComptableUnitRG5() throws Exception {
        assertThrows(FunctionalException.class,
                ()->{
                    vEcritureComptable.setLibelle("Libelle");
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(123),
                            null));
                    vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                            null, new BigDecimal(123),
                            null));
                    manager.checkEcritureComptableUnit(vEcritureComptable);
                });

    }


    @Test
    void deleteEcritureComptable() {
        vEcritureComptable.setId(-1);
    manager.deleteEcritureComptable(-1);
    }
}
