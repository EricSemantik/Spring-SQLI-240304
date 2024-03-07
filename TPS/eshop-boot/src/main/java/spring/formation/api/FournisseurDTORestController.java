package spring.formation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.api.response.AdresseResponse;
import spring.formation.api.response.FournisseurResponse;
import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

@RestController
@RequestMapping("/api/fournisseurDTO")
public class FournisseurDTORestController {
    private IFournisseurRepository fournisseurRepository;

    public FournisseurDTORestController(IFournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @GetMapping("")
    public List<FournisseurResponse> findAll() {
        return this.fournisseurRepository.findAll().stream().map(four -> {
        	FournisseurResponse fournisseurResponse = FournisseurResponse.convert(four);
        	
        	List<Long> idProduits = four.getProduits().stream().map(prod -> prod.getId()).toList();
        	fournisseurResponse.setIdProduits(idProduits);
        	
        	return fournisseurResponse;
        }).toList();
    }

    @GetMapping("/{id}")
    public FournisseurResponse findById(@PathVariable Long id) {
        Optional<Fournisseur> optFournisseur = this.fournisseurRepository.findById(id);

        Fournisseur fournisseur = optFournisseur.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur non trouvé"));
   
        FournisseurResponse fournisseurResponse = FournisseurResponse.convert(fournisseur);
    	
    	List<Long> idProduits = fournisseur.getProduits().stream().map(prod -> prod.getId()).toList();
    	fournisseurResponse.setIdProduits(idProduits);
    	
    	List<AdresseResponse> adresses = fournisseur.getAdresses().stream().map(AdresseResponse::convert).toList();
//    	List<AdresseResponse> adresses = fournisseur.getAdresses().stream().map(adr -> AdresseResponse.convert(adr)).toList();
    	fournisseurResponse.setAdresses(adresses);
    	
    	
    	return fournisseurResponse;
    }

    @PostMapping("")
    public Fournisseur create(@RequestBody Fournisseur fournisseur) {
        return this.fournisseurRepository.save(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur create(@RequestBody Fournisseur fournisseur, @PathVariable Long id) {
        if(!this.fournisseurRepository.existsById(id) || !id.equals(fournisseur.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return this.fournisseurRepository.save(fournisseur);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        this.fournisseurRepository.deleteById(id);
    }
}
