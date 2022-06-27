package lab.lhss.ecommerce.cascadeoperations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

public class CascadePersistTypeTest extends EntityManagerTest {

    //@Test
    public void testPersistOrderWithOrderItems() {

        Client client = entityManager.find(Client.class, 1);
        Item item = entityManager.find(Item.class, 1);

        Order order = new Order();
        order.setCreateDate(LocalDateTime.now());
        order.setClient(client);
        order.setTotal(BigDecimal.ONE);
        order.setStatus(OrderStatus.PAID);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(new OrderItemId());
        orderItem.setItem(item);
        orderItem.setItemPrice(item.getPrice());
        orderItem.setAmount(1);
        orderItem.setOrder(order);

        order.setItems(Collections.singletonList(orderItem)); // CascadeTipe.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertFalse(orderVerify.getItems().isEmpty());

    }

    @Test
    public void testPersistOrderItemWithOrder() {
        // Persist only orderItem and order will be persisted too even without cascade property!
        // because order is part of orderItem primary key
        Client client = entityManager.find(Client.class, 1);
        Item item = entityManager.find(Item.class, 1);

        Order order = new Order();
        order.setCreateDate(LocalDateTime.now());
        order.setClient(client);
        order.setTotal(BigDecimal.ONE);
        order.setStatus(OrderStatus.PAID);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(new OrderItemId());
        orderItem.setItem(item);
        orderItem.setOrder(order); // No need for CascadeType.PERSIST because order is part of OrderItem PK.
        orderItem.setItemPrice(item.getPrice());
        orderItem.setAmount(1);

        entityManager.getTransaction().begin();
        entityManager.persist(orderItem);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertNotNull(orderVerify.getId());
    }

    //@Test
    public void testPersistOrderWithClient() {

        Client client = new Client();
        client.setGender(Gender.FEMALE);
        client.setName("Ana");
        client.setCpf("12345");
        client.setBirthDate(LocalDate.of(1980, 1, 30));

        Order order = new Order();
        order.setCreateDate(LocalDateTime.now());
        order.setClient(client); // CascadeType.PERSIST
        order.setTotal(BigDecimal.TEN);
        order.setStatus(OrderStatus.WAITING);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Client clientVerify = entityManager.find(Client.class, client.getId());
        Assert.assertNotNull(clientVerify);
    }

}
