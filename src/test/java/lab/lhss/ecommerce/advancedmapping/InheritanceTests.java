package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InheritanceTests extends EntityManagerTest {

    @Test
    public void testInheritanceMappedSuperclass() {

        Client client = new Client();

        client.setName("Kevin McAlister");
        client.setCpf("12345");
        client.setGender(Gender.MALE);

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
    }


    @Test
    public void testIncludeNewPayment() {
        Order order = entityManager.find(Order.class, 2);

        BankSlipPayment payment = new BankSlipPayment();
        payment.setOrder(order);
        payment.setStatus(PaymentStatus.PROCESSING);
        payment.setBarCode("1234");

        entityManager.getTransaction().begin();
        entityManager.persist(payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertNotNull((orderVerify.getPayment()));
    }

}
