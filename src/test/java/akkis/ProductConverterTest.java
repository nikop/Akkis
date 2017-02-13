package akkis;
import static org.junit.Assert.*;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import akkis.converters.ProductConverter;

import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)
public class ProductConverterTest {

	@Mock
	FacesContext mockFacesContext; 
	UIComponent mockUIComponent; 
	
	@Mock
	Object mockObject;
	
	@Test
	public void testGetAsObject() {
		ProductConverter testattava = new ProductConverter();
		assertEquals(null, testattava.getAsObject(mockFacesContext, mockUIComponent, null));
	}
	
	@Test
	public void testGetAsString() {
		ProductConverter testattava = new ProductConverter();
		assertEquals("", testattava.getAsString(mockFacesContext, mockUIComponent, null));
	}
}