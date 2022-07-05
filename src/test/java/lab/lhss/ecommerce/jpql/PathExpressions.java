package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressions extends EntityManagerTest {

    @Test
    public void usePathExpressionTest() {
        String jpql = "select o.client.name , o.client.cpf from Order o";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void findOrderWithSpecificProductTest() {
        String jpql = "select o from Order o join fetch o.items i where i.id.itemId = 1";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void findOrderWithSpecificProductTest2 () {
        String jpql = "select o from OrderItem i " +
                " join Order o on o.id = i.id.orderId  " +
                " where i.id.itemId = 1";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }
}
