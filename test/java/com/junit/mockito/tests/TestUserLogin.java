package com.junit.mockito.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bean.User;
import com.dao.DaoFactory;
import com.dao.interfaces.UserDao;
import com.form.UserAuthForm;


class TestUserLogin {

	private static DaoFactory daofactory;
	private static UserDao userdao;
	private static UserAuthForm userauth;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daofactory = Mockito.mock(DaoFactory.class);
		userdao = Mockito.mock(UserDao.class);
		userauth = new UserAuthForm(userdao);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		daofactory = null;
		userdao = null;
		userauth = null;
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAuthWithValidCredentials() {
		User user = new User();
		user.setEmail("salma@gmail.com");

		
		User userset = new User("sellami", "salma", "salma@gmail.com", "F", "0673829292", "25d4824df60545545311648b993309da936607a8fe537f5dc10b3fb38da31aab","C");
		try {
			Mockito.when(userdao.findSpecificUser(Mockito.argThat(new IsSameUser(user)))).thenReturn(userset);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Error unfound account");
		}
		
		User userLogin = new User();


		User userretrieved = userauth.traiterUser("salma@gmail.com", "salma", userLogin);
		
		assertThat(userretrieved).isNotNull();

		assertThat(userretrieved.getTel()).isEqualTo("0673829292");
		assertThat(userretrieved.getNom()).isEqualTo("sellami");
		assertThat(userretrieved.getPrenom()).isEqualTo("salma");
	}
	
	@Test
	void testAuthWithInvalidCredentials() {
		User user = new User();
		user.setEmail("rayan@gmail.com");
		
		try {
			Mockito.when(userdao.findSpecificUser(Mockito.argThat(new IsSameUser(user)))).thenReturn(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User userLogin = new User();		

//		assertThrows(Exception.class, ()->userauth.traiterUser("rayan@gmail.com", "rayan", userLogin));
//	    assertEquals("Utilisateur introuvable. Merci de réessayer", exception.getMessage());
//	
	}

}
