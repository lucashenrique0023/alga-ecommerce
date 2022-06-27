package lab.lhss.ecommerce.cascadeoperations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class CascadeTypeMergeTest extends EntityManagerTest {

    //@Test
    public void testMergeOrderAndOrderItem() {

        Client client = entityManager.find(Client.class, 1);
        Item item = entityManager.find(Item.class, 1);

        Order order = new Order();
        order.setId(1);
        order.setClient(client);
        order.setStatus(OrderStatus.WAITING);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(new OrderItemId());
        orderItem.getId().setOrderId(order.getId());
        orderItem.getId().setItemId(item.getId());
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setAmount(3);
        orderItem.setItemPrice(item.getPrice());

        order.setItems(Collections.singletonList(orderItem)); // CascadeType.MERGE

        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
        entityManager.clear();

        OrderItem orderItemVerify = entityManager.find(OrderItem.class, orderItem.getId());
        Assert.assertTrue(orderItemVerify.getAmount().equals(3));
    }
}
