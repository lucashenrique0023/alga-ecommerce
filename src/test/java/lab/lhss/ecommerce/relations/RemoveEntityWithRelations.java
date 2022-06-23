package lab.lhss.ecommerce.relations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RemoveEntityWithRelations extends EntityManagerTest {

    @Test
    public void verifyRelation() {

        Client client = entityManager.find(Client.class, 1);

        Order order = new Order();
        order.setClient(client);
        order.setCreateDate(LocalDateTime.now());
        order.setTotal(BigDecimal.ZERO);
        order.setStatus(OrderStatus.WAITING);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        OrderItem orderItem = new OrderItem();
        orderItem.setId(new OrderItemId());
        orderItem.setOrder(order);
        orderItem.setItem(entityManager.find(Item.class, 1));
        orderItem.setItemPrice(orderItem.getItem().getPrice());
        orderItem.setAmount(1);

        entityManager.getTransaction().begin();
        entityManager.persist(orderItem);
        entityManager.getTransaction().commit();
        entityManager.clear();

        order = entityManager.find(Order.class, order.getId());
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
