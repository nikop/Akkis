package akkis;

import static org.junit.Assert.*;

//import org.junit.Before;
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

}

