package com.junit.mockito.itests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.bean.Offre;
import com.dao.DaoFactory;
import com.dao.interfaces.OffreDao;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OffreDaoITest {
	static int newoffre;
	private static DaoFactory daofactory;
	private OffreDao offredao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daofactory = DaoFactory.getInstance();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		offredao = daofactory.getOffreDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void InsertOffreTest() {
		//Create Offre
		Offre o = new Offre(12, 0.3F, "", 10, 10, 10);
		try {
			newoffre = offredao.insertOffre(o);
			//Retrieve
			Offre offretrieved = offredao.selectOffre(newoffre);
			//Assert
			assertThat(offretrieved.getCarton_grand()).isEqualTo(10);
			assertThat(offretrieved.getCarton_moyen()).isEqualTo(10);
			assertThat(offretrieved.getCarton_petit()).isEqualTo(10);
			assertThat(offretrieved.getReduction_offre()).isEqualTo(0.3F);
			assertThat(offretrieved.getDescription()).isEqualTo("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
	void ListerOffreTest() {
		//Retrieve Offre
		try {
			System.out.println(newoffre);
			Offre Offreselected = offredao.selectOffre(newoffre);
			//Assert
			assertThat(Offreselected.getCarton_grand()).isEqualTo(10);
			assertThat(Offreselected.getCarton_moyen()).isEqualTo(10);
			assertThat(Offreselected.getCarton_petit()).isEqualTo(10);
			assertThat(Offreselected.getReduction_offre()).isEqualTo(0.3F);
			assertThat(Offreselected.getDescription()).isEqualTo("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	@Order(4)
	void DeleteUpOffreTest() {
		
		Offre o = new Offre(newoffre, 0.35F, "", 12, 10, 10, 10);
		//Retrieve Offre
		try {
			boolean isUpdated = offredao.updateOffre(o);
			Offre Offreselected = offredao.selectOffre(newoffre);
			//Assert

			assertThat(isUpdated).isTrue();
			assertThat(Offreselected.getReduction_offre()).isEqualTo(0.35F);
			assertThat(Offreselected.getDescription()).isEqualTo("");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Update failed");
		}
		
		
		//Retrieve Offre
		try {
			boolean isdeleted = offredao.deleteOffre(newoffre);
			//Assert
			assertThat(isdeleted).isTrue();
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Delete failed");
		}
	}

}
