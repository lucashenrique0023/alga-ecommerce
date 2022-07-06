package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Invoice;
import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.model.PaymentStatus;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class ParametersTest extends EntityManagerTest {

    @Test
    public void simpleParameterTest() {

        // Using Named and Positional parameters example
        String jpql = "select o from Order o  join o.payment pay where o.id = ?1 and pay.status = :paymentStatus";

        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        typedQuery.setParameter(1, 1);
        typedQuery.setParameter("paymentStatus", PaymentStatus.PROCESSING);
        List<Order> orders = typedQuery.getResultList();
        Assert.assertTrue(orders.size() == 1
                && orders.get(0).getId() == 1
                && orders.get(0).getPayment().getStatus().equals(PaymentStatus.PROCESSING));
    }

    @Test
    public void dateParameterTest() {
        String jpql = "select i from Invoice i where i.emissionDate <= :date";

        TypedQuery<Invoice> typedQuery = entityManager.createQuery(jpql, Invoice.class);
        typedQuery.setParameter("date", new Date(), TemporalType.TIMESTAMP);
        List<Invoice> list = typedQuery.getResultList();
        Assert.assertTrue(list.size() == 1);
    }

}
