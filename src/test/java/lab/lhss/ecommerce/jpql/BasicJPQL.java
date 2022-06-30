package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class BasicJPQL extends EntityManagerTest {


    @Test
    public void findById() {
        TypedQuery<Order> typedQuery = entityManager.createQuery(
                "select o from Order o where o.id = 1",
                Order.class
        );

//        This throw errors on empty results or multiple results
        Order order = typedQuery.getSingleResult();
        Assert.assertNotNull(order);

//        This avoid errors on empty results or multiple results
//        List<Order> orderList = typedQuery.getResultList();
//        Assert.assertFalse(orderList.isEmpty());
    }

}
