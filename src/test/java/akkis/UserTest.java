package akkis;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

public class UserTest {

	@Test
	public void passwordCheck() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		User user = new User();
		user.setPassword("password");
		
		assertTrue(user.checkPasswordForLogin("password"));
	}

}
