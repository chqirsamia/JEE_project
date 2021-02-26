package com.xadmin.plateforme.dao.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.xadmin.plateforme.dao.DaoFactory;

import java.sql.SQLException;

public class InitialisationDaoFactory implements ServletContextListener {
    private static final String ATT_FACT = "daofactory";
    private DaoFactory daoFactory;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        try {
            daoFactory = DaoFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        servletContext.setAttribute(ATT_FACT,this.daoFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}