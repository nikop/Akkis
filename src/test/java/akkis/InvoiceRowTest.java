package akkis;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvoiceRowTest {

	@Test
	public void invoiceRowSum() {
		
		InvoiceRow row = new InvoiceRow("Test", 10);
		
		assertEquals(10.0, row.getRowTotal(), 0.0);	
	}

	@Test
	public void invoiceRowSumMultiple() {
		
		InvoiceRow row = new InvoiceRow("Test", 10, 10);
		
		assertEquals(100.0, row.getRowTotal(), 0.0);	
	}
}
