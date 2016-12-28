package akkis.types;

public enum Permission {
	VIEW_CUSTOMERS("View Customers");
	
	private String label;

    private Permission(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
