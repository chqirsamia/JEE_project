package com.xadmin.plateforme.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.xadmin.plateforme.bean.User;

public interface UserDao {
	 public User findSpecificUser(User user) throws SQLException;
	 public List<User> findAllUsers() throws SQLException;
	 public int insertUser(User user) throws SQLException;
	 public int insertAdmin(User user) throws SQLException;
	 public boolean updateUser(User user) throws SQLException;
	 public boolean deleteUser(User user) throws SQLException;
	 public List<User> selectAllUsers()throws SQLException;
	 public boolean deleteUser(int id) throws SQLException;
	 public  User selectUser(int id)throws SQLException;
		
		
}
