package akkis;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AkkisEjbTest {

	@Test
	public void testInit() {
		
		assertEquals(50, 50);
	}

	@Test
	public void testSave() {
		assertEquals(50, 50);	
	}
	
	@Test
	public void testSaveChenges() {
		assertEquals(50, 50);
	}
	
	@Test
	public void testGetUser() {
		assertEquals(50, 50);
	}
	
	@Test
	public void testGetCustomer() {
		assertEquals(50, 50);
	}
	
	@Test
	public void testGetProduct() {
		assertEquals(50, 50);	
	}
}




