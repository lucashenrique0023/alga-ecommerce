package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Gender;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class SecondaryTableTests extends EntityManagerTest {

    @Test
    public void testSecondaryTable() {

        Client client = new Client();
        client.setName("Lord Valdemort");
        client.setGender(Gender.MALE);
        client.setBirthDate(LocalDate.of(1926, 12, 31));
        client.setCpf("3242545");

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Client clientVerity = entityManager.find(Client.class, client.getId());

        Assert.assertNotNull(clientVerity.getGender());
    }
}
