package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransactionOperationTests extends EntityManagerTest {

    @Test
    public void openAndCloseTransaction() {

        Product product = new Product();

        entityManager.getTransaction().begin();

        //entityManager.persist(product);
        //entityManager.merge(product);
        //entityManager.remove(product);

        entityManager.getTransaction().commit();
    }

    @Test
    public void insertFirstObject() {
        Product product = new Product();
        product.setId(2);
        product.setName("Camera Canon");
        product.setDescription("The Best definition in your pictures");
        product.setPrice(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        // Saves the object on entity manager memory (becomes managed object)
        // This call could be after begin() and commit() methods
        entityManager.persist(product);

        //flush() Forces JPA to send everything on entityManager memory to database
        // commit() executes flush before
        entityManager.flush();

        // Store object on database
        entityManager.getTransaction().commit();

        // Clear Entity Manager memory cache
        //entityManager.clear();
        //entityManager = entityManagerFactory.createEntityManager();

        // Does create a select for this, catches from entity manager memory.
        Product productVerify = entityManager.find(Product.class, product.getId());

        Assert.assertNotNull(productVerify);
    }

    @Test
    public void removeObject() {
        // Product product = new Product();
        // product.setId(3);

        // Makes object managed, keep in entity manager memory.
        Product product = entityManager.find(Product.class,3);

        entityManager.getTransaction().begin();

        // java.lang.IllegalArgumentException: Removing a detached instance lab.lhss.ecommerce.model.Product#3
        // If the product does not exists on Databse JPA ignores it, but in this case are throwing IllegalArgumentException
        // To remove, JPA needs to know the existence of the object, we need to make it managed using find().
        entityManager.remove(product);

        entityManager.getTransaction().commit();

        Product productVerify = entityManager.find(Product.class, 3);
        Assert.assertNull(productVerify);
    }
}
