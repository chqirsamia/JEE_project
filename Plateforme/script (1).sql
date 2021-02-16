#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Carton
#------------------------------------------------------------

CREATE TABLE Carton(
        id_carton      Int  Auto_increment  NOT NULL ,
        type_carton    Varchar (2) NOT NULL ,
        libelle_carton Varchar (20) NOT NULL
	,CONSTRAINT Carton_PK PRIMARY KEY (id_carton)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE Users(
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

CREATE TABLE Offre(
        id_offre        Int  Auto_increment  NOT NULL ,
        reduction_offre Float NOT NULL ,
        id         Int NOT NULL
	,CONSTRAINT Offre_PK PRIMARY KEY (id_offre)

	,CONSTRAINT Offre_User_FK FOREIGN KEY (id) REFERENCES User(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CartonDemandé
#------------------------------------------------------------

CREATE TABLE CartonDemande(
        id_carton        Int NOT NULL ,
        id          Int NOT NULL ,
        NbrCartonDemande Int NOT NULL
	,CONSTRAINT CartonDemande_PK PRIMARY KEY (id_carton,id)

	,CONSTRAINT CartonDemande_Carton_FK FOREIGN KEY (id_carton) REFERENCES Carton(id_carton)
	,CONSTRAINT CartonDemande_User0_FK FOREIGN KEY (id) REFERENCES User(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: OffreCarton
#------------------------------------------------------------

CREATE TABLE OffreCarton(
        id_offre        Int NOT NULL ,
        id_carton       Int NOT NULL ,
        NbrCartonDecide Int NOT NULL
	,CONSTRAINT OffreCarton_PK PRIMARY KEY (id_offre,id_carton)

	,CONSTRAINT OffreCarton_Offre_FK FOREIGN KEY (id_offre) REFERENCES Offre(id_offre)
	,CONSTRAINT OffreCarton_Carton0_FK FOREIGN KEY (id_carton) REFERENCES Carton(id_carton)
)ENGINE=InnoDB;

