package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransactionOperationTests extends EntityManagerTest {

    @Test
    public void openAndCloseTransaction() {

        item item = new item();

        entityManager.getTransaction().begin();

        //entityManager.persist(product);
        //entityManager.merge(product);
        //entityManager.remove(product);

        entityManager.getTransaction().commit();
    }

    @Test
    public void insertFirstObject() {
        item item = new item();
        item.setId(2);
        item.setName("Camera Canon");
        item.setDescription("The Best definition in your pictures");
        item.setPrice(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        // Saves the object on entity manager memory (becomes managed object)
        // This call could be after begin() and commit() methods
        entityManager.persist(item);

        //flush() Forces JPA to send everything on entityManager memory to database
        // commit() executes flush before
        entityManager.flush();

        // Store object on database
        entityManager.getTransaction().commit();

        // Clear Entity Manager memory cache
        //entityManager.clear();
        //entityManager = entityManagerFactory.createEntityManager();

        // Does create a select for this, catches from entity manager memory.
        item itemVerify = entityManager.find(item.class, item.getId());

        Assert.assertNotNull(itemVerify);
    }

    @Test
    public void removeObject() {
        // Product product = new Product();
        // product.setId(3);

        // Makes object attached to avoid IllegalArgumentException when calls remove().
        item item = entityManager.find(item.class,3);

        entityManager.getTransaction().begin();

        // java.lang.IllegalArgumentException: Removing a detached instance lab.lhss.ecommerce.model.Product#3
        // If the product does not exists on Databse JPA ignores it, but in this case are throwing IllegalArgumentException
        // To remove, JPA needs to know the existence of the object, we need to make it managed using find().
        entityManager.remove(item);

        entityManager.getTransaction().commit();

        item itemVerify = entityManager.find(item.class, 3);
        Assert.assertNull(itemVerify);
    }

    @Test
    public void updateObject() {
        // All Attributes must be fulfill, otherwise attributes not set gonna be null.
        // If Object ID not exists on DB, a Insert gonna be executed
        item item = new item();
        item.setId(10);
        item.setName("Kindle Paperwhite");
        item.setDescription("Conheca o novo Kindle");
        item.setPrice(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();

        entityManager.clear();

        item itemVerify = entityManager.find(item.class, item.getId());
        Assert.assertNotNull(itemVerify);
        Assert.assertEquals("Kindle Paperwhite", itemVerify.getName());
    }

    @Test
    public void insertObjectWithMerge() {
        item item = new item();
        item.setId(4);
        item.setName("Headset");
        item.setDescription("Best Sound Quality");
        item.setPrice(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();

        entityManager.clear();
        item itemVerify = entityManager.find(item.class, item.getId());

        Assert.assertNotNull(itemVerify);
    }

    @Test
    public void showDifferencePersistAndMerge() {
        item itemPersist = new item();
        itemPersist.setId(5);
        itemPersist.setName("Smartphone One Plus");
        itemPersist.setDescription("The best processor!");
        itemPersist.setPrice(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(itemPersist); // Saves this instance on entityManager
        itemPersist.setName("Smartphone Two Plus"); // Updates Instance ON DB
        entityManager.getTransaction().commit();

        entityManager.clear();
        item itemVerifyPersist = entityManager.find(item.class, itemPersist.getId());

        Assert.assertNotNull(itemVerifyPersist);

        item itemMerge = new item();
        itemMerge.setId(6);
        itemMerge.setName("Notebook Dell");
        itemMerge.setDescription("The best for your company!");
        itemMerge.setPrice(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        item itemMergeCopyInstance = entityManager.merge(itemMerge); // Makes a copy of this instance and stores on entityManager
        itemMergeCopyInstance.setName("Notebook Dell 3"); // This line have effect!
        itemPersist.setName("Notebook Dell 2"); // Does not have any effect
        entityManager.getTransaction().commit();

        entityManager.clear();
        item itemVerifyMerge = entityManager.find(item.class, itemMerge.getId());

        Assert.assertNotNull(itemVerifyMerge);
    }

    @Test
    public void updateManagedObject() {
        item item = entityManager.find(item.class, 1);

        entityManager.getTransaction().begin();
        item.setName("Kindle Paperwhite 2 Gen");
        entityManager.getTransaction().commit();

        entityManager.clear();

        item itemVerify = entityManager.find(item.class, item.getId());
        Assert.assertEquals("Kindle Paperwhite 2 Gen", itemVerify.getName());
    }
}
