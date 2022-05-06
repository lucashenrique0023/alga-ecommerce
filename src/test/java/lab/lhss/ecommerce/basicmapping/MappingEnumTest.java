package lab.lhss.ecommerce.basicmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Gender;
import org.junit.Assert;
import org.junit.Test;

public class MappingEnumTest extends EntityManagerTest {

    @Test
    public void testEnum() {
        Client client = new Client();
        client.setId(4L);
        client.setName("José Mineiro");
        client.setGender(Gender.MALE);

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Client clienteVerificacao = entityManager.find(Client.class, client.getId());
        Assert.assertNotNull(clienteVerificacao);
    }

}
