package akkis;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class InvoiceTest {

	@Test
	public void test() {
		Invoice inv = new Invoice();
		
		InvoiceRow row1 = mock(InvoiceRow.class);
		InvoiceRow row2 = mock(InvoiceRow.class);
		InvoiceRow row3 = mock(InvoiceRow.class);
		
		when(row1.getRowTotal()).thenReturn(10.0);
		when(row2.getRowTotal()).thenReturn(20.0);
		when(row3.getRowTotal()).thenReturn(30.0);
		
		inv.addRow(row1);
		inv.addRow(row2);
		inv.addRow(row3);
		
		assertEquals(60.0, inv.calculateSum(), 0.0);
		assertEquals(60.0, inv.getSum(), 0.0);
	}

}
