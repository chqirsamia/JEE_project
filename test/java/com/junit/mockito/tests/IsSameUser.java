package com.junit.mockito.tests;

import org.mockito.ArgumentMatcher;
import com.xadmin.plateforme.bean.User;

public class IsSameUser implements ArgumentMatcher<User> {

	private User user;
	
	public IsSameUser(User user) {
		this.user = user;
	}
	

	@Override
	public boolean matches(User other) {
	      if (other == null) return false;
	      System.out.println("It is not null");
	      System.out.println("other > "+other.getEmail());
	      System.out.println("user > "+user.getEmail());
	      return other.getEmail().equals(user.getEmail()) &&
	             other.getPassword().equals(user.getPassword());	}
}
