package com.junit.mockito.itests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bean.Offre;
import com.bean.User;
import com.dao.DaoFactory;
import com.dao.interfaces.UserDao;
import com.form.UserInscriptionForm;

class UserDaoITest {
	static int idinserted;
	private static DaoFactory daofactory;
	private UserDao userdao;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daofactory = DaoFactory.getInstance();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		userdao = daofactory.getUserDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void insertUserITest() {
	//ARRANGE
		idinserted = 0;
		User user = new User();
		UserInscriptionForm form = new UserInscriptionForm(userdao);
		//User info
		form.traiterPrenom("Lina", user);
		form.traiterNom("Lamyaghri", user);
		form.traiterMotDePasse("lina", "lina", user);
		form.traiterTel("0638292202", user);
		user.setSexe("F");
		user.setRole("C");
		user.setEmail("lina@gmail.com");
		
	//ACT
		//Insertion fail
		try {
			idinserted = userdao.insertUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Test failed due to insertion error !");
		}
		
		assertThat(idinserted).isNotEqualTo(-1);
	}
	
	
	@Test
	void listUserITest() {
		//Retrieving fail
		User userRetrieved = null;

			try {
				userRetrieved = userdao.findSpecificUser(idinserted);
			} catch (SQLException e) {
				e.printStackTrace();
				fail("Test failed due to listing error !");
			}
		//ASSERT	
			assertThat(userRetrieved.getId()).isEqualTo(idinserted);
			assertThat(userRetrieved.getNom()).isEqualToIgnoringCase("lamyaghri");
			assertThat(userRetrieved.getPrenom()).isEqualToIgnoringCase("lina");
			assertThat(userRetrieved.getEmail()).isEqualToIgnoringCase("lina@gmail.com");
			assertThat(userRetrieved.getTel()).isEqualToIgnoringCase("0638292202");	
	}
	
	@Test
	void DeleteUserITest() {
		
		try {
			boolean isdeleted = userdao.deleteUser(idinserted);
			//Assert
			assertThat(isdeleted).isTrue();
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Delete failed");
		}
	}
	
}
