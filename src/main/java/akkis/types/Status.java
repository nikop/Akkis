package akkis.types;

public enum Status {
	
	CONTACT("Contact"),
	LEAD("Lead"),
	ONGOING("OnGoing"),
	CUSTOMER("Customer"),
	OLDCUSTOMER("Old Customer"),
	RETIRED("Retired");
	
	private String label;

    private Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}


