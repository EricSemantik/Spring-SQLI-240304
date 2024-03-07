package spring.formation.model;

public enum Roles {
	GUEST, USER, ADMIN, SUPER_ADMIN;
	
	public String authority() {
		return "ROLE_" + name();
	}
}
