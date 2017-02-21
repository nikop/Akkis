package akkis;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ManagedBean
@Entity
@RequestScoped
@NamedQueries({
	@NamedQuery(name = "invoiceRowsForInvoice", query = "SELECT i from InvoiceRow i WHERE i.invoice = :invoice"),
	@NamedQuery(name = "invoiceRowById", query = "SELECT i from InvoiceRow i WHERE i.id = :id"),

})
public class InvoiceRow {

	@Id
	@SequenceGenerator(name = "id_seq_invoice_row", sequenceName = "InvoiceRow_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_invoice_row")
	private long id;
	
	@ManyToOne
	private Invoice invoice;
	
	private String text;
	
	@Min(1)
	private int amount;
	
	private double unitPrice;
	
	public InvoiceRow()
	{
		amount = 1;
	}
	
	public InvoiceRow(String rowText, double price)
	{
		text = rowText;
		amount = 1;
		unitPrice = price;
	}
	
	public InvoiceRow(String rowText, int units, double price)
	{
		text = rowText;
		amount = units;
		unitPrice = price;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public double getRowTotal() {
		return unitPrice * amount;
	}
}
