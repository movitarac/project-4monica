# MyERP
[![Build Status](https://travis-ci.org/movitarac/project-4monica.svg?branch=master)](https://travis-ci.org/movitarac/project-4monica)
[![Coverage Status](https://coveralls.io/repos/github/movitarac/project-4monica/badge.svg?branch=master)](https://coveralls.io/github/movitarac/project-4monica?branch=master)
## Organisation du répertoire

*   `doc` : documentation
*   `docker` : répertoire relatifs aux conteneurs _docker_ utiles pour le projet
    *   `dev` : environnement de développement
*   `src` : code source de l'application


## Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/dev/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)



### Lancement

    cd docker/dev
    docker-compose up


### Arrêt

    cd docker/dev
    docker-compose stop


### Remise à zero

    cd docker/dev
    docker-compose stop
    docker-compose rm -v
    docker-compose up

## Corrections et Additions 

* `getTotalCredit()` dans `com.dummy.myerp.model.bean.comptabilite.EcritureComptable` : mettre `getCredit()` au lieu de `getCredit()`
* `isEquilibree()` dans `com.dummy.myerp.model.bean.comptabilite.EcritureComptable` : mettre `equals(); ` au lieu de `compareTo()`	
* `sqlContext.xml` dans `com.dummy.myerp.consumer` : ajouter `,` entre debit-credit  
* Changer JUnit 4 -> JUnit 5
* Ajouter un attribut, getter-setter pour String journalCode dans `com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable` 
* Ajouter une methode `getSequenceEcritureComptable()` dans `ComptabiliteDao` + `ComptabiliteDaoImpl` et ajouter la requete `SQLgetSequenceEcritureComptable` dans sqlContext.xml   
* Ajouter des methodes `insertSequenceEcritureComptable()` + `updateSequenceEcritureComptable()`  dans `ComptabiliteDao` + `ComptabiliteDaoImpl` et ajouter des requetes `SQLinsertSequenceEcritureComptable` + `SQLupdateSequenceEcritureComptable` dans sqlContext.xml 
  
            
