package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import org.junit.Assert;
import org.junit.Test;

public class FindRecordsTest extends EntityManagerTest {


    @Test
    public void searchById() {
        Item item = entityManager.find(Item.class, 1);
//        Product product = entityManager.getReference(Product.class, 1);

        Assert.assertNotNull(item);
        Assert.assertEquals("Kindle", item.getName());
    }

    @Test
    public void updateReference() {
        Item item = entityManager.find(Item.class, 1);
        item.setName("Micro");
        System.out.println(item.getName());
        entityManager.refresh(item);
        System.out.println("Refresh!");
        System.out.println(item.getName());
        Assert.assertEquals("Kindle", item.getName());
    }
}
