package akkis;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class DeliveryTest {

	@Test
	public void invoiceCreated() {
		Delivery d = new Delivery();
		
		Product p1 = mock(Product.class);
		Product p2 = mock(Product.class);
		
		when(p1.getName()).thenReturn("Tuote 1");
		when(p1.getPrice()).thenReturn(10.0);
		when(p2.getName()).thenReturn("Tuote 2");
		when(p2.getPrice()).thenReturn(20.0);
		
		DeliveryProduct dp1 = mock(DeliveryProduct.class);
		DeliveryProduct dp2 = mock(DeliveryProduct.class);
		
		when(dp1.getProduct()).thenReturn(p1);
		when(dp2.getProduct()).thenReturn(p2);
		
		d.addProduct(dp1);
		d.addProduct(dp2);
		
		Invoice inv = d.createInvoice();
		
		assertEquals(2, inv.getRows().size());
		
		assertEquals(30.0, inv.getSum(), 0.0);
	}

}
