package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class MapsIdTests extends EntityManagerTest {

    @Test
    public void insertPaymentWithMapsId() {

        Order order = entityManager.find(Order.class, 1);

        entityManager.getTransaction().begin();

        Invoice invoice = new Invoice();
        invoice.setXml(new byte[1]);
        invoice.setEmissionDate(new Date(System.currentTimeMillis()));
        invoice.setOrder(order);

        entityManager.persist(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Invoice invoiceVerify = entityManager.find(Invoice.class, order.getId());
        Assert.assertNotNull(invoiceVerify);
    }

    @Test
    public void insertOrderItemWithMapsId() {

        Client client = entityManager.find(Client.class, 1);
        Item item = entityManager.find(Item.class, 1);

        Order order = new Order();
        order.setClient(client);
        order.setCreateDate(LocalDateTime.now());
        order.setStatus(OrderStatus.WAITING);
        order.setTotal(item.getPrice());

        OrderItem itemPedido = new OrderItem();
        itemPedido.setId(new OrderItemId());
        itemPedido.setOrder(order);
        itemPedido.setItem(item);
        itemPedido.setItemPrice(item.getPrice());
        itemPedido.setAmount(1);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        OrderItem orderItemVerify = entityManager.find(
                OrderItem.class, new OrderItemId(order.getId(), item.getId()));
        Assert.assertNotNull(orderItemVerify);

    }
}
