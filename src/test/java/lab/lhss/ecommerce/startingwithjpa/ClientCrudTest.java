package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import org.junit.Assert;
import org.junit.Test;

public class ClientCrudTest extends EntityManagerTest {

    @Test
    public void createClient() {

        Client client = new Client();
        client.setId(4L);
        client.setName("Lucas");

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Client clientPersisted  = entityManager.find(Client.class, client.getId());

        Assert.assertNotNull(clientPersisted);
    }

    @Test
    public void findClient() {

        Client client = entityManager.find(Client.class, 1L);

        Assert.assertNotNull(client);
        Assert.assertEquals("Lucas", client.getName());
    }

    @Test
    public void updateClient() {

        Client client = entityManager.find(Client.class, 2L);

        entityManager.getTransaction().begin();
        client.setName("Souza");
        entityManager.getTransaction().commit();


        entityManager.clear();

        Client clientUpdated = entityManager.find(Client.class, 2L);
        Assert.assertNotNull(clientUpdated);
        Assert.assertEquals("Souza", clientUpdated.getName());
    }

    @Test
    public void removeClient() {

        Client client = entityManager.find(Client.class, 3L);

        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();

        entityManager.find(Client.class, 3L);

        Assert.assertNull(client);
    }

}
