package akkis.types;

public enum Role {
	VIEW_USERS("View Users"),
	EDIT_USERS("Edit Users"),
	VIEW_CUSTOMERS("View Customers"),
	EDIT_CUSTOMERS("Edit Customers"),
	VIEW_COMPANIES("View Companies"),
	EDIT_COMPANIES("Edit Companies"),
	VIEW_DELIVERIES("View Deliveries"),
	EDIT_DELIVERIES("Edit Deliveries"),
	VIEW_INVOICES("View Invoices"),
	EDIT_INVOICES("Edit Invoices"),	
	VIEW_PRODUCTS("View Products"),
	EDIT_PRODUCTS("Edit Products"),
	ADMIN("Admin (Can do everything)");
	
	private String label;

    private Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
