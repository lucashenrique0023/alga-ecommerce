package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class NamedQuery extends EntityManagerTest {

    @Test
    public void namedQueryTest() {

        TypedQuery<Item> typedQuery = entityManager.createNamedQuery("Item.listByCategory", Item.class);
        typedQuery.setParameter("category", 2);
        List<Item> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void namedQueryFromFileTest() {
        TypedQuery<Item> typedQuery = entityManager.createNamedQuery("Item.list", Item.class);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void namedQueryOrmXmlGetOrdersByClient() {
        TypedQuery<Order> typedQuery = entityManager.createNamedQuery("Order.getByClient", Order.class);
        typedQuery.setParameter("client", 1);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }
}
