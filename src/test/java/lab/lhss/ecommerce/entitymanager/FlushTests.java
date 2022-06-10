package lab.lhss.ecommerce.entitymanager;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.model.OrderStatus;
import org.junit.Test;

public class FlushTests extends EntityManagerTest {

    @Test//(expected = Exception.class)
    public void verifyRelation() {
        try {
            entityManager.getTransaction().begin();
            Order order = entityManager.find(Order.class, 1);
            order.setStatus(OrderStatus.PAID);

            entityManager.flush();
//            if (order.getPayment() == null) {
//                entityManager.getTransaction().rollback();
//            }




            entityManager.getTransaction().commit();

            Order order2 = entityManager.find(Order.class, 1);
            System.out.println("--------------------------");
            System.out.println(order2.getStatus());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
