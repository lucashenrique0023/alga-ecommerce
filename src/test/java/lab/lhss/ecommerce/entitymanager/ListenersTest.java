package lab.lhss.ecommerce.entitymanager;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Invoice;
import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.model.OrderStatus;
import org.junit.Test;

public class ListenersTest extends EntityManagerTest {

    @Test
    public void entityListenerTest() {

        Order order = entityManager.find(Order.class, 1);

        entityManager.getTransaction().begin();
        order.setStatus(OrderStatus.PAID);
        entityManager.getTransaction().commit();
    }
}
