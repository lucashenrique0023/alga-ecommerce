package lab.lhss.ecommerce.cascadeoperations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class CascadeTypeRemoveTest extends EntityManagerTest {

    /*
    * Are no need to use Cascade REMOVE for
    * @ManyToMany or @ManyToOne relations
    * the use gonna cause Constraint Violations and BUH errors!
    */


    @Test
    public void testRemoveItemAndRelationWithoutCascadeRemove() {

        Item item = entityManager.find(Item.class, 1);

        Assert.assertFalse(item.getCategories().isEmpty());

        entityManager.getTransaction().begin();
        item.getCategories().clear();
        entityManager.getTransaction().commit();
        entityManager.clear();

        Item itemVerify = entityManager.find(Item.class, item.getId());
        Assert.assertTrue(itemVerify.getCategories().isEmpty());
    }

    //@Test
    public void removingOrphalItem() {

        Order order = entityManager.find(Order.class, 1);

        Assert.assertFalse(order.getItems().isEmpty());

        entityManager.getTransaction().begin();
        order.getItems().clear();  // cascade = CascadeType.PERSIST, orphanRemoval = true
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderVerify = entityManager.find(Order.class, order.getId());
        Assert.assertTrue(orderVerify.getItems().isEmpty());
    }

    //@Test
    public void removingOrderAndOrphalItem() {

        Order order = entityManager.find(Order.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(order); // orphanRemoval = true OR CascadeType.REMOVE
        entityManager.getTransaction().commit();

        entityManager.clear();
    }

}
