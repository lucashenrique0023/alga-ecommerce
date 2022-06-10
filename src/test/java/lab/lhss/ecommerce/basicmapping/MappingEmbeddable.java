package lab.lhss.ecommerce.basicmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Address;
import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.model.OrderStatus;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MappingEmbeddable extends EntityManagerTest {

    @Test
    public void analyzeMappingEmbeddedObject() {
        Address address = new Address();
        address.setZip("00000-00");
        address.setStreet("Rua das Laranjeiras");
        address.setNumber("123");
        address.setAdditionalInfo("Centro");
        address.setCity("Uberlândia");
        address.setState("MG");

        Order order = new Order();
        order.setId(1);
        order.setCreateDate(LocalDateTime.now());
        order.setStatus(OrderStatus.WAITING);
        order.setTotal(new BigDecimal(1000));
        order.setAddress(address);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertNotNull(orderVerify);
        Assert.assertNotNull(orderVerify.getAddress());
        Assert.assertNotNull(orderVerify.getAddress().getZip());
    }

}
