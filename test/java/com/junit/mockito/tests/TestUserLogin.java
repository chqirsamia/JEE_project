package com.junit.mockito.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.UserDao;
import com.xadmin.plateforme.forms.UserAuthForm;

class TestUserLogin {

	private static DaoFactory daofactory;
	private static UserDao userdao;
	private static UserAuthForm userauth;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daofactory = Mockito.mock(DaoFactory.class);
		userdao = Mockito.mock(UserDao.class);
		userauth = new UserAuthForm(userdao);
		System.out.println(daofactory);
		System.out.println(userdao);
		System.out.println(userauth);
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
		user.setPassword("salma");
		
		User userset = new User("sellami", "salma", "salma@gmail.com", "F", "0673829292", "C");
		try {
			Mockito.when(userdao.findSpecificUser(Mockito.argThat(new IsSameUser(user)))).thenReturn(userset);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Error unfound account");
		}
		
		User userLogin = new User();
		userLogin.setEmail("salma@gmail.com");
		userLogin.setPassword("salma");

		User userretrieved = null;
		try {
			userretrieved = userdao.findSpecificUser(userLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertThat(userretrieved).isNotNull();

		assertThat(userretrieved.getTel()).isEqualTo("0673829292");
		assertThat(userretrieved.getNom()).isEqualTo("sellami");
		assertThat(userretrieved.getPrenom()).isEqualTo("salma");
	}
	
	@Test
	void testAuthWithInvalidCredentials() {
		User user = new User();
		user.setEmail("salma@gmail.com");
		user.setPassword("salma");
		
		User userset = new User("sellami", "salma", "salma@gmail.com", "F", "0673829292", "C");
		try {
			//Mockito.when(userdao.findSpecificUser(user)).thenReturn(userset);
			Mockito.when(userdao.findSpecificUser(Mockito.argThat(new IsSameUser(user)))).thenReturn(userset);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Error unfound account");
		}
		
		User userLogin = new User();
		userLogin.setEmail("sara@gmail.com");
		userLogin.setPassword("sara");
		
		User userretrieved = null;
		try {
			userretrieved = userdao.findSpecificUser(userLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertThat(userretrieved).isNotNull();

		assertThat(userretrieved.getTel()).isEqualTo("0673829292");
		assertThat(userretrieved.getNom()).isEqualTo("sellami");
		assertThat(userretrieved.getPrenom()).isEqualTo("salma");
	}

}
