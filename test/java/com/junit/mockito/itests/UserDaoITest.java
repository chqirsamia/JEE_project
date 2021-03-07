package com.junit.mockito.itests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.UserDao;
import com.xadmin.plateforme.forms.UserAuthForm;
import com.xadmin.plateforme.forms.UserInscriptionForm;

class UserDaoITest {

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
		int idinserted = 0;
		User user = new User();
		User userRetrieved = null;
		UserInscriptionForm form = new UserInscriptionForm(userdao);
		//User info
		form.traiterPrenom("Salma", user);
		form.traiterNom("Louadghiri", user);
		form.traiterMotDePasse("salma", "salma", user);
		form.traiterTel("0672829303", user);
		user.setSexe("F");
		user.setRole("C");
		user.setEmail("salma@gmail.com");
		
	//ACT
		//Insertion fail
		try {
			idinserted = userdao.insertUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Test failed due to insertion error !");
		}
		//Retrieving fail
		try {
			userRetrieved = userdao.findSpecificUser(idinserted);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Test failed due to listing error !");
		}
	//ASSERT	
		assertThat(idinserted).isEqualTo(userRetrieved.getId());
		assertThat(user.getNom()).isEqualTo(userRetrieved.getNom());
		assertThat(user.getPassword()).isEqualTo(userRetrieved.getPassword());
		assertThat(user.getPrenom()).isEqualTo(userRetrieved.getPrenom());
		assertThat(user.getEmail()).isEqualTo(userRetrieved.getEmail());
		assertThat(user.getRole()).isEqualTo(userRetrieved.getRole());
		assertThat(user.getSexe()).isEqualTo(userRetrieved.getSexe());
	}
	
	

}
