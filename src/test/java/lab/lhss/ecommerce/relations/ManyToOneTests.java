package lab.lhss.ecommerce.relations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ManyToOneTests extends EntityManagerTest {

    @Test
    public void veirifyRelation() {

        Client client = entityManager.find(Client.class, 1);

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING);
        order.setOrderDate(LocalDateTime.now());
        order.setClient(client);
        order.setTotal(new BigDecimal(100));

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertNotNull(orderVerify.getClient());
    }

    @Test
    public void verifyRelationOrderItem() {

        Client client = entityManager.find(Client.class,1);
        Item item = entityManager.find(Item.class, 1);

        Order order = new Order();

        order.setStatus(OrderStatus.WAITING);
        order.setOrderDate(LocalDateTime.now());
        order.setClient(client);
        order.setTotal(new BigDecimal(100));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setItemPrice(item.getPrice());
        orderItem.setAmount(4);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.persist(orderItem);
        entityManager.getTransaction().commit();

        entityManager.clear();

        OrderItem orderItemVerify = entityManager.find(OrderItem.class, 1);
        Assert.assertNotNull(orderItemVerify.getOrder());
        Assert.assertNotNull(orderItemVerify.getItem());
    }
}
