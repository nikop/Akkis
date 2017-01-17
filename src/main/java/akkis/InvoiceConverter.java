package akkis;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

@ManagedBean
@RequestScoped
public class InvoiceConverter implements Converter {

    @EJB
    private AkkisEjb ejb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            Long id = Long.valueOf(value);
            return ejb.getInvoice(id);
        } catch (NumberFormatException e) {
            throw new ConverterException("The value is not a valid ID: " + value, e);
        }
    }

    @Override    
    public String getAsString(FacesContext context, UIComponent component, Object value) {        
        if (value == null) {
            return "";
        }

        if (value instanceof Invoice) {
            Long id = ((Invoice) value).getId();
            return (id != null) ? String.valueOf(id) : null;
        } else {
            throw new ConverterException("The value is not a valid instance: " + value);
        }
    }
}