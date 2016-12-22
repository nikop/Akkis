package akkis;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import akkis.types.Status;

@ManagedBean
@ApplicationScoped
public class Data {

	public Status[] getStatuses() {
        return Status.values();
    }
	
}
