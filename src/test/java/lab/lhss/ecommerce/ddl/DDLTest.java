package lab.lhss.ecommerce.ddl;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import org.junit.Test;

import javax.persistence.PersistenceException;

public class DDLTest extends EntityManagerTest {

    @Test(expected = PersistenceException.class)
    public void testDDLUniqueCPFConstraintClientError() {
        Client client = new Client();
        client.setName("Lucas");
        client.setCpf("10293848595");

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }
}
