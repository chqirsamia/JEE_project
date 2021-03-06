#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: carton
#------------------------------------------------------------

CREATE TABLE carton(
        id_carton      Int  Auto_increment  NOT NULL ,
        type_carton    Varchar (2) NOT NULL ,
        libelle_carton Varchar (20) NOT NULL,
		prix int  NOT NULL
	,CONSTRAINT Carton_PK PRIMARY KEY (id_carton)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: users
#------------------------------------------------------------

CREATE TABLE users(
        id     Int  Auto_increment  NOT NULL ,
        nom    Varchar (50) NOT NULL ,
        prenom Varchar (50) NOT NULL ,
        email  Varchar (100) NOT NULL ,
        tel    Varchar (10) NOT NULL ,
		sexe Varchar (15) NOT NULL ,
        password Varchar (15) NOT NULL ,
        role   Varchar (10) NOT NULL
	,CONSTRAINT User_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Offre
#------------------------------------------------------------

CREATE TABLE offre(
        id_offre        Int  Auto_increment  NOT NULL ,
        reduction_offre Float NOT NULL ,
        id         Int NOT NULL
		description Varchar (1000) NOT NULL ,
		carton_moyen int not null,
		carton_petit int not null,
		carton_grand int not null
	,CONSTRAINT Offre_PK PRIMARY KEY (id_offre)

	,CONSTRAINT Offre_User_FK FOREIGN KEY (id) REFERENCES User(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: demande
#------------------------------------------------------------

CREATE TABLE demande(
        id        Int Auto_increment NOT NULL ,
       date_creation Date NOT NULL ,
	   etat Varchar (2) NOT NULL ,
	   id_client       Int   NOT NULL 
	,CONSTRAINT demande_PK PRIMARY KEY (id)

	,CONSTRAINT demande_User_FK FOREIGN KEY (id_client) REFERENCES User(id)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: cartondemande
#------------------------------------------------------------

CREATE TABLE cartondemande(
        id        Int NOT NULL ,
        id          Int NOT NULL ,
        NbrCartonDemande Int NOT NULL
	,CONSTRAINT CartonDemande_PK PRIMARY KEY (id_carton,id)

	,CONSTRAINT CartonDemande_Carton_FK FOREIGN KEY (id_carton) REFERENCES Carton(id_carton)
	,CONSTRAINT CartonDemande_User0_FK FOREIGN KEY (id) REFERENCES User(id)
)ENGINE=InnoDB;


