package akkis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import akkis.types.InvoiceStatus;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@Entity
@RequestScoped
@NamedQueries({
	@NamedQuery(name = "searchAllInvoices", query = "SELECT i from Invoice i ORDER BY i.date DESC"),
	@NamedQuery(name = "invoiceById", query = "SELECT i from Invoice i WHERE i.id = :id"),
	@NamedQuery(name = "invoicesForDelivery", query = "SELECT i from Invoice i WHERE i.delivery = :delivery"),
	@NamedQuery(name = "invoicesByStatus", query = "SELECT i from Invoice i WHERE i.status = :status ORDER BY i.date"),
})
public class Invoice {

	@Id
	@SequenceGenerator(name = "id_seq_invoice", sequenceName = "Invoice_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_invoice")
	private long id;
	
	@ManyToOne
	private Delivery delivery;
	
	@DecimalMin("0.0")
	@DecimalMax("99999.99")
	private double sum;
	
	private Date date;
	
	@Min(1)
	@Max(14)
	private int duePeriod;
	
	@Size(min = 4, message = "Write Info text, at least 4 characters")
	private String infoText; 
	
	private InvoiceStatus status;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy="invoice")
	private List<InvoiceRow> rows = new ArrayList<InvoiceRow>();	
	
	public Invoice() {	
		date = new Date();
		status = InvoiceStatus.NOT_SENT;
		duePeriod = 14;
	}	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}
	
	public void addRow(InvoiceRow row)
	{
		row.setInvoice(this);
		rows.add(row);
	}

	public List<InvoiceRow> getRows() {
		return rows;
	}

	public void setRows(List<InvoiceRow> rows) {
		this.rows = rows;
	}
	
	public double calculateSum()
	{
		double sum = 0;
		
		for (InvoiceRow invoiceRow : rows) {
			sum += invoiceRow.getRowTotal();
		}
		
		return sum;
	}
	
	public boolean canAddItems()
	{
		return status == InvoiceStatus.NOT_SENT;
	}
	
	public boolean isOpen()
	{
		return status == InvoiceStatus.OPEN;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", sum=" + sum + ", date=" + date
				+ ", duePeriod=" + duePeriod + ", infoText=" + infoText + "]";
	}
	
}
