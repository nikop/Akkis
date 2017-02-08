package akkis;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import akkis.types.InvoiceStatus;
import akkis.types.Role;
import akkis.types.Status;

@ManagedBean
@ApplicationScoped
public class Data {

	public Status[] getStatuses() {
        return Status.values();
    }
	
	public Role[] getRoles() {
        return Role.values();
    }
	
	public InvoiceStatus[] getInvoiceStatuses() {
        return InvoiceStatus.values();
    }
	
}
