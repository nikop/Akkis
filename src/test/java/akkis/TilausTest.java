package akkis;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class TilausTest {

	@Mock
	AkkisEjb mockAkkisEjb;
	
	@Test
	public void testAdd() {
		assertEquals(50, 50);
	}
	
	@Test
	public void testgetTotal() {
		Tilaus testattava = new Tilaus();
		assertEquals(0.0, testattava.getTotal(),0.1);
	}
}

	