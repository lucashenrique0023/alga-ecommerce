package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Payment;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InheritanceTests extends EntityManagerTest {

    @Test
    public void testInheritanceMappedSuperclass() {

        Client client = new Client();

        client.setName("Kevin McAlister");

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Client clientVerify = entityManager.find(Client.class, client.getId());
        Assert.assertNotNull(clientVerify);
        Assert.assertEquals(client, clientVerify);
        Assert.assertEquals(client.getName(), clientVerify.getName());
    }

    @Test
    public void testFindPaymentsFromAbstractPaymentClass() {

        List<Payment> payments = entityManager.createQuery("select p from Payment p")
                .getResultList();
        payments.forEach(p -> System.out.println(p.getId()));
        Assert.assertFalse(payments.isEmpty());
        Assert.assertEquals(2, payments.size());
    }


}
