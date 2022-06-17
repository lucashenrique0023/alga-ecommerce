package lab.lhss.ecommerce.relations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Category;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

public class RemoveEntityWithRelations extends EntityManagerTest {

    @Test
    public void verifyRelation() {

        Order order = entityManager.find(Order.class, 5);
        Assert.assertFalse(order.getItems().isEmpty());

        entityManager.getTransaction().begin();
        order.getItems().forEach(o -> entityManager.remove(o));
        entityManager.remove(order);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertNull(orderVerify);
    }
}
