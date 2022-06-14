package lab.lhss.ecommerce.relations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

public class OneToOneTests extends EntityManagerTest {

    @Test
    public void verifyPaymentRelation() {

        Order order = entityManager.find(Order.class, 1);

        CreditCardPayment payment = new CreditCardPayment();
        payment.setOrder(order);
        payment.setCardNumber("1234");
        payment.setStatus(PaymentStatus.PROCESSING);

        entityManager.getTransaction().begin();
        entityManager.persist(payment);
        entityManager.getTransaction().commit();

        entityManager.clear();

        CreditCardPayment paymentVerify = entityManager.find(CreditCardPayment.class, payment.getId());
        Assert.assertNotNull(paymentVerify.getOrder());
    }

    @Test
    public void verifyInvoiceRelation() {

        Order order = entityManager.find(Order.class, 1);

        Invoice invoice = new Invoice();
        invoice.setXml(new byte[1]);
        invoice.setOrder(order);
        invoice.setEmissionDate(new Date());

        entityManager.getTransaction().begin();
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertNotNull(orderVerify.getItems());
    }

}
