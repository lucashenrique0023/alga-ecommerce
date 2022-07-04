package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.CreditCardPayment;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTests extends EntityManagerTest {

    // INNER JOIN //

    @Test
    public void innerJoinOneToOneRelationTest() {

        // There are multiple Orders but only 1 has payment, this inner join will retrieve only this one!
        String jpql = "select o from Order o inner join o.payment p";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> orders = typedQuery.getResultList();
        Assert.assertTrue(orders.size() == 1);
    }

    @Test
    public void innerJoinOneToOneProjectionTest() {

        // Returning Both (Order and Payment) for every result.
        String jpql = "select o, p from Order o inner join o.payment p";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertTrue(Order.class.equals(list.get(0)[0].getClass()));
        Assert.assertTrue(CreditCardPayment.class.equals(list.get(0)[1].getClass()));
    }

    @Test
    public void innerJoinWithWhereTest() {
        //String jpql = "select distinct o from Order o join o.items i on i.itemPrice > 10";
        String jpql = "select distinct o from Order o join o.items i where i.itemPrice > 10";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> list = typedQuery.getResultList();

        Assert.assertTrue(list.size() == 5);
    }

    @Test
    public void innerJoinOneToManyProjectionTest() {

        // Returning Multiple Itens for Single Order
        String jpql = "select o, i from Order o join o.items i";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertTrue(list.size() == 6);
    }

    // LEFT JOIN //

    @Test
    public void leftJoinTest() {

        String jpql = "select o from Order o left join o.payment p on p.status = 'PROCESSING'";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

    }

}
