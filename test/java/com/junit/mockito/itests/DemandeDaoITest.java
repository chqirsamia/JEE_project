package com.junit.mockito.itests;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.bean.CartonDemande;
import com.bean.Demande;
import com.classesneeded.LigneDemandeSpec;
import com.dao.DaoFactory;
import com.dao.interfaces.CartonDemandeDao;
import com.dao.interfaces.DemandeDao;

class DemandeDaoITest {
	static int newdmd;
	private static DaoFactory daofactory;
	private DemandeDao dmd;
	private CartonDemandeDao cdmd;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daofactory = DaoFactory.getInstance();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dmd = daofactory.getDemandeDao();
		cdmd = daofactory.getCartonDemDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void InsertDemandeLigne() {
		
		try {
			//CREATE NEW DEMANDE
			newdmd = dmd.insertDemande(1);
			//CREATE NEW CARTONDEMANDE
			CartonDemande ld = new CartonDemande(1, newdmd, 10);
			cdmd.addCartonDemande(ld);
			
			//Retrieving data
			List<LigneDemandeSpec> dmdretrieved = cdmd.findCartonDemande(newdmd);
			assertThat(dmdretrieved.size()).isEqualTo(1);
			
			Demande d = dmd.selectDemande(newdmd);
			assertThat(d.getEtat()).isEqualTo("NF");
			assertThat(d.getId()).isEqualTo(newdmd);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void UpdateDemande() {
		
		try {
			//DELETE DEMANDE
			dmd.updateDemande(newdmd);
			
			//Retrieving data
			Demande d = dmd.selectDemande(newdmd);
			assertThat(d.getEtat()).isEqualTo("F");
			assertThat(d.getId()).isEqualTo(newdmd);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	void ListerDemande() {
//		
//		try {
//			
//			//Retrieving data
//			Demande d = dmd.selectDemande(newdmd);
//			assertThat(d.getId_client()).isEqualTo(1);
//			assertThat(d.getId()).isEqualTo(newdmd);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	void DeleteDemande() {
		boolean isDmd = false;
		List<LigneDemandeSpec> dmdretrieved = null;
		try {
			//find all command lines related to specific command
			dmdretrieved = cdmd.findCartonDemande(newdmd);
			
			//delete all line commands
			for(int i=0; i<dmdretrieved.size(); i++) {
				//delete all line commands
				cdmd.deleteCartonDemande(dmdretrieved.get(i).getCd());
			}
			
			//delete demande
			isDmd = dmd.deleteDemande(newdmd);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertThat(isDmd).isTrue();
	}
}
