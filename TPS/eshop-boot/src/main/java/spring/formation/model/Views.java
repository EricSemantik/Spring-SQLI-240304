package spring.formation.model;

public interface Views {
	public interface ViewCommon {}
	
	public interface ViewPersonne extends ViewCommon {}
	
	public interface ViewFournisseur extends ViewPersonne {}
	
	public interface ViewFournisseurDetail extends ViewFournisseur {}
	
	public interface ViewProduit extends ViewCommon {}
}
