package akkis;
import static org.junit.Assert.*;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import akkis.converters.CustomerConverter;

import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)
public class CustomerConverterTest {
	@Mock
	FacesContext mockFacesContext; 
	UIComponent mockUIComponent; 
	@Mock
	Object mockObject;
	
	@Test
	public void testGetAsObject() {
		CustomerConverter testattava = new CustomerConverter();
		assertEquals(null, testattava.getAsObject(mockFacesContext, mockUIComponent, null));
	}
	
	@Test
	public void testGetAsString() {
		CustomerConverter testattava = new CustomerConverter();
		assertEquals("", testattava.getAsString(mockFacesContext, mockUIComponent, null));
	}
}
