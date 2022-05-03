package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Product;
import org.junit.Assert;
import org.junit.Test;

public class FindRecordsTest extends EntityManagerTest {


    @Test
    public void searchById() {
        Product product = entityManager.find(Product.class, 1);
//        Product product = entityManager.getReference(Product.class, 1);

        Assert.assertNotNull(product);
        Assert.assertEquals("Kindle", product.getName());
    }

    @Test
    public void updateReference() {
        Product product = entityManager.find(Product.class, 1);
        product.setName("Micro");
        System.out.println(product.getName());
        entityManager.refresh(product);
        System.out.println("Refresh!");
        System.out.println(product.getName());
        Assert.assertEquals("Kindle", product.getName());
    }
}
