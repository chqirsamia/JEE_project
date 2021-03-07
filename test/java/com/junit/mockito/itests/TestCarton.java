package com.junit.mockito.itests;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.dao.DaoFactory;
import com.dao.interfaces.CartonDao;



class TestCarton {

	private static DaoFactory daofactory;
	private CartonDao cd;
	
	@BeforeAll
	static void initDao() {
		try {
			daofactory = DaoFactory.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeEach
	void initCarton() {
		cd = daofactory.getCartondao();
	}
	
	@ParameterizedTest(name="Carton de type {0} a l'id {1}")
	@CsvSource({"P,1", "M,2", "G,3"})
	void testerTypeIdCarton(String type, int idBdd) {
		//ARRANGE
		
		//ACT
		int idrecupere = cd.getIdByType(type);
		//ASSERT
		assertThat(idrecupere).isEqualTo(idBdd);
	}


}
