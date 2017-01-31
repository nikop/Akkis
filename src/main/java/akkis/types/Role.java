package akkis.types;

public enum Role {
	SELLER("Seller"),
	CUSTOMERSERVICE("Customer Service"),
	BILLER("Biller"),
	BOSS("Boss"),
	ADMIN("Admin");
	
	private String label;

    private Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
