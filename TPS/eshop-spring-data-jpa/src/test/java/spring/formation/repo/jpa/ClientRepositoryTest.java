package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Client;
import spring.formation.repo.IClientRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ClientRepositoryTest {
	@Autowired
	private IClientRepository repoClient;

	@Test
	public void testUpdate() {
		// ARRANGE
		Client client = new Client();
		client.setNom("SULTAN");
		client.setPrenom("Eric");
		client.setAdresse("eric@semantik.fr");

		client = repoClient.save(client);
		
		// ACT
		Optional<Client> optClient = repoClient.findById(client.getId());
		if(optClient.isEmpty()) {
			Assert.fail("Client inexistant");
		}
		
		Client clientFind = optClient.get();
		clientFind.setNom("SULTANA");
		clientFind.setPrenom("Erica");
		clientFind.setAdresse("erica@semantik.fr");
		
		clientFind = repoClient.save(clientFind);
		
		// ASSERT
		optClient = repoClient.findById(clientFind.getId());
		if(optClient.isEmpty()) {
			Assert.fail("Client Find inexistant");
		}
		
		clientFind = optClient.get();
		
		assertEquals("SULTANA", clientFind.getNom());
		assertEquals("Erica", clientFind.getPrenom());
		assertEquals("erica@semantik.fr", clientFind.getAdresse());
	}
}
