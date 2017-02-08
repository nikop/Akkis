package akkis.types;

public enum InvoiceStatus {
	
	NOT_SENT("Not Sent"),
	OPEN("Open"),
	PAID("Paid"),
	VOIDED("Voided");
	
	private String label;

    private InvoiceStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}