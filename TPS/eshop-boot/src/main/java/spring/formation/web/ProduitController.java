package spring.formation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

@Controller
@RequestMapping("/produit")
public class ProduitController {
	
	@Autowired
	private IProduitRepository repoProduit;

	@GetMapping({"", "/list"}) // ETAPE 1 : Réception de la Request
	public String list(Model model) {
		List<Produit> produits = repoProduit.findAll(); // ETAPE 2 : Récupérer les données
		
		model.addAttribute("mesProduits", produits); // ETAPE 3 : Renseigner le Model
		
		return "produit/list";		
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("produit", new Produit());
		
		return "produit/form";
	}
	
	@GetMapping("/{idProduit}/edit")
	public String edit(@PathVariable("idProduit") Long id, Model model) {
		Optional<Produit> optProduit = repoProduit.findById(id);
		
		if(optProduit.isEmpty()) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}
		
		model.addAttribute("produit", optProduit.get());
		
		return "produit/form";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam(name = "id", required = false) Long id, @RequestParam("libelle") String libelle, @RequestParam(name = "prixAchat", required = false) Double prixAchat, @RequestParam(name = "prixVente", required = false) Double prixVente, @RequestParam("reference") String reference, @RequestParam("modele") String modele, @RequestParam(name = "stock", required = false, defaultValue = "0") int stock) {
		Produit produit = null;
		
		if(id != null) {
			Optional<Produit> optProduit = repoProduit.findById(id);
			
			if(optProduit.isEmpty()) {
				throw new ResponseStatusException(HttpStatusCode.valueOf(404));
			}
			
			produit = optProduit.get();
		} else {
			produit = new Produit();
		}
		
		produit.setLibelle(libelle);
		produit.setPrixAchat(prixAchat);
		produit.setPrixVente(prixVente);
		produit.setReference(reference);
		produit.setModele(modele);
		produit.setStock(stock);
		
		repoProduit.save(produit);
		
		return "redirect:list";
	}
	
	@PostMapping("/saveBis")
	public String saveBis(@ModelAttribute("produit") Produit produit) {
		if(produit.getId() != null && !repoProduit.existsById(produit.getId())) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}
		
		repoProduit.save(produit);
		
		return "redirect:list";
	}
	
	
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}
	
	@GetMapping("/{idProduit}/delete")
	public String delete(@PathVariable("idProduit") Long id) {
		if(!repoProduit.existsById(id)) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}
		
		repoProduit.deleteById(id);
		
		return "redirect:../list";
	}

}
