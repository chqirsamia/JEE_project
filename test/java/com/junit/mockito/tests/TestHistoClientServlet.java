package com.junit.mockito.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bean.Offre;
import com.dao.DaoFactory;
import com.dao.interfaces.DemandeDao;
import com.dao.interfaces.OffreDao;
import com.web.histoclient;

class TestHistoClientServlet {
	private static DaoFactory daofactory;
	private static HttpSession session;
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static DemandeDao demande;
    private static OffreDao offre;
    private static ServletContext ctx;
    private static ServletConfig config;
    private static final String PAGE = "/WEB-INF/histoclient.jsp";

    
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daofactory = Mockito.mock(DaoFactory.class);

		request= Mockito.mock(HttpServletRequest.class);       
        response = Mockito.mock(HttpServletResponse.class); 
        session = Mockito.mock(HttpSession.class);
		ctx = Mockito.mock( ServletContext.class );
		config = Mockito.mock(ServletConfig.class );
        
        demande = Mockito.mock(DemandeDao.class);
        offre = Mockito.mock(OffreDao.class);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHistoServlet() {
		histoclient servlet = Mockito.mock(histoclient.class);
		servlet.setDemande(demande);
		servlet.setOffre(offre);
		try {
			servlet.init(config);
			
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Mockito.when(servlet.getServletContext()).thenReturn(ctx);		
		List<Offre> ofs = new ArrayList<Offre>();
		ofs.add(new Offre(12, 0.3F, "", 10, 10, 2));
		try {
			Mockito.when(request.getSession()).thenReturn(session);
			Mockito.when(request.getSession().getAttribute("userId")).thenReturn(1);
			Mockito.when(offre.listerOffre()).thenReturn(ofs);
			try {
				Mockito.when(demande.findAllDmds(1)).thenReturn(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			servlet.doGet(request, response);
			Mockito.when(response.getContentType()).thenReturn("text/html");

			//Assert
			assertThat(response.getContentType()).isEqualTo("text/html");
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
