package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CompositeKeyTest extends EntityManagerTest {

    @Test
    public void saveItem() {

        entityManager.getTransaction().begin();

        Client client = entityManager.find(Client.class, 1);
        Item item = entityManager.find(Item.class, 1);

        Order order = new Order();
        order.setClient(client);
        order.setCreateDate(LocalDateTime.now());
        order.setStatus(OrderStatus.WAITING);
        order.setTotal(item.getPrice());

        entityManager.persist(order);
        entityManager.flush();

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        orderItem.setItemId(item.getId());
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setItemPrice(item.getPrice());
        orderItem.setAmount(1);


        entityManager.persist(orderItem);
        entityManager.getTransaction().commit();

        entityManager.clear();

        OrderItem orderItemVerify = entityManager.find(OrderItem.class, new OrderItemId(order.getId(), item.getId()));
        Assert.assertNotNull(orderItemVerify);
    }
}
