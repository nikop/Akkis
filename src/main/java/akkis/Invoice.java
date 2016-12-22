package akkis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@Entity
@NamedQuery(name = "searchAllInvoices", query = "SELECT i from Invoice i") 
public class Invoice {

	@Id
	@SequenceGenerator(name = "id_seq_invoice", sequenceName = "Invoice_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_invoice")

	private long id;
	private Delivery delivery;
	private double sum;
	private Date date;
	private int duePeriod;
	private String infoText; 
	
	public Invoice() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuePeriod() {
		return duePeriod;
	}

	public void setDuePeriod(int duePeriod) {
		this.duePeriod = duePeriod;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", delivery=" + delivery + ", sum=" + sum
				+ ", date=" + date + ", duePeriod=" + duePeriod + ", infoText="
				+ infoText + "]";
	}
	
	
	
}
