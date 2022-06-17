package lab.lhss.ecommerce.relations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OneToManyTests extends EntityManagerTest {

    @Test
    public void veirifyRelationClientOrder() {

        Client client = entityManager.find(Client.class, 1);

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING);
        order.setCreateDate(LocalDateTime.now());
        order.setTotal(new BigDecimal(100));

        order.setClient(client);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Client clientVerify = entityManager.find(Client.class, client.getId());
        Assert.assertFalse(clientVerify.getOrders().isEmpty());

    }

    @Test
    public void veirifyRelationOrderItemOrder() {

        Client client = entityManager.find(Client.class, 1);

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING);
        order.setCreateDate(LocalDateTime.now());
        order.setTotal(new BigDecimal(100));
        order.setClient(client);

        Item item = entityManager.find(Item.class, 1);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(new OrderItemId());
        orderItem.setItemPrice(item.getPrice());
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setAmount(5);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.persist(orderItem);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertFalse(orderVerify.getItems().isEmpty());

    }
}
