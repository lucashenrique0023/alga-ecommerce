package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ElementCollectionTests extends EntityManagerTest {

    @Test
    public void testElementCollection() {

        Item item = entityManager.find(Item.class, 1);

        entityManager.getTransaction().begin();
        item.setTags(Arrays.asList("digital-reader", "electronic"));
        entityManager.getTransaction().commit();
        entityManager.clear();

        Item itemVerify = entityManager.find(Item.class, 1);

        itemVerify.getTags().forEach(System.out::println);
        Assert.assertFalse(itemVerify.getTags().isEmpty());


    }
}
