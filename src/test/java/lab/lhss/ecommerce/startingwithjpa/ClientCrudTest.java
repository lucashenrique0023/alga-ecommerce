package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Gender;
import org.junit.Assert;
import org.junit.Test;

public class ClientCrudTest extends EntityManagerTest {

    @Test
    public void createClient() {

        Client client = new Client();
        client.setName("Fernanda");
        client.setCpf("12442");
        client.setGender(Gender.FEMALE);

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Client clientPersisted  = entityManager.find(Client.class, client.getId());

        Assert.assertNotNull(clientPersisted);
    }

    @Test
    public void findClient() {

        Client client = entityManager.find(Client.class, 1);

        Assert.assertNotNull(client);
        Assert.assertEquals("Lucas", client.getName());
    }

    @Test
    public void updateClient() {

        Client client = entityManager.find(Client.class, 2);

        entityManager.getTransaction().begin();
        client.setName("Souza");
        entityManager.getTransaction().commit();


        entityManager.clear();

        Client clientUpdated = entityManager.find(Client.class, 2);
        Assert.assertNotNull(clientUpdated);
        Assert.assertEquals("Souza", clientUpdated.getName());
    }

    @Test
    public void removeClient() {

        Client client = new Client();
        client.setName("Name Name");
        client.setCpf("127362");
        client.setGender(Gender.MALE);


        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Client clientVerify = entityManager.find(Client.class, client.getId());

        entityManager.getTransaction().begin();
        entityManager.remove(clientVerify);
        entityManager.getTransaction().commit();

        Client clientAssert = entityManager.find(Client.class, client.getId());

        Assert.assertNull(clientAssert);
    }

}


