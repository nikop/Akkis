package akkis;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvoiceTest {

	@Test
	public void test() {
		Invoice inv = new Invoice();
		
		inv.addRow(new InvoiceRow("Item 1", 10));
		inv.addRow(new InvoiceRow("Item 2", 1, 10));
		inv.addRow(new InvoiceRow("Item 3", 2, 10));
		
		assertEquals(40.0, inv.calculateSum());
		
		assertEquals(40.0, inv.getSum());
	}

}
