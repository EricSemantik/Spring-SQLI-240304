package spring.formation.api;

import java.util.List;
import java.util.Optional;

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

import com.fasterxml.jackson.annotation.JsonView;

import spring.formation.model.Fournisseur;
import spring.formation.model.Views;
import spring.formation.repo.IFournisseurRepository;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurRestController {
    private IFournisseurRepository fournisseurRepository;

    public FournisseurRestController(IFournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @GetMapping("")
    @JsonView(Views.ViewFournisseur.class)
    public List<Fournisseur> findAll() {
        return this.fournisseurRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.ViewFournisseurDetail.class)
    public Fournisseur findById(@PathVariable Long id) {
        Optional<Fournisseur> optFournisseur = this.fournisseurRepository.findById(id);

        return optFournisseur.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur non trouvé"));
    }

    @PostMapping("")
    @JsonView(Views.ViewFournisseur.class)
    public Fournisseur create(@RequestBody Fournisseur fournisseur) {
        return this.fournisseurRepository.save(fournisseur);
    }

    @PutMapping("/{id}")
    @JsonView(Views.ViewFournisseur.class)
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
