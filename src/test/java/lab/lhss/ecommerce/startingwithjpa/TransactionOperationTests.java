package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionOperationTests extends EntityManagerTest {

    @Test
    public void openAndCloseTransaction() {

        Item item = new Item();

        entityManager.getTransaction().begin();

        //entityManager.persist(product);
        //entityManager.merge(product);
        //entityManager.remove(product);

        entityManager.getTransaction().commit();
    }

    @Test
    public void insertFirstObject() {
        Item item = new Item();
        item.setCreateDate(LocalDateTime.now());
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
        Item itemVerify = entityManager.find(Item.class, item.getId());

        Assert.assertNotNull(itemVerify);
    }

    @Test
    public void removeObject() {
        // Product product = new Product();
        // product.setId(3);

        // Makes object attached to avoid IllegalArgumentException when calls remove().
        Item item = entityManager.find(Item.class,4);

        entityManager.getTransaction().begin();

        // java.lang.IllegalArgumentException: Removing a detached instance lab.lhss.ecommerce.model.Product#3
        // If the product does not exists on Databse JPA ignores it, but in this case are throwing IllegalArgumentException
        // To remove, JPA needs to know the existence of the object, we need to make it managed using find().
        entityManager.remove(item);

        entityManager.getTransaction().commit();

        Item itemVerify = entityManager.find(Item.class, 4);
        Assert.assertNull(itemVerify);
    }

    @Test
    public void updateObject() {
        // All Attributes must be fulfill, otherwise attributes not set gonna be null.
        Item item = entityManager.find(Item.class, 5);
        item.setName("Gopro 3");
        item.setDescription("Fine Camera");
        item.setPrice(new BigDecimal(3599));

        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Item itemVerify = entityManager.find(Item.class, item.getId());
        Assert.assertNotNull(itemVerify);
        Assert.assertEquals("Gopro 3", itemVerify.getName());
    }

    @Test
    public void insertObjectWithMerge() {
        Item item = new Item();
        item.setCreateDate(LocalDateTime.now());
        item.setName("Headset");
        item.setDescription("Best Sound Quality");
        item.setPrice(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        Item savedItem = entityManager.merge(item);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Item itemVerify = entityManager.find(Item.class, savedItem.getId());

        Assert.assertNotNull(itemVerify);
    }

    @Test
    public void showDifferencePersistAndMerge() {
        Item itemPersist = new Item();
        itemPersist.setCreateDate(LocalDateTime.now());
        itemPersist.setName("Smartphone One Plus");
        itemPersist.setDescription("The best processor!");
        itemPersist.setPrice(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(itemPersist); // Saves this instance on entityManager
        itemPersist.setName("Smartphone Two Plus"); // Updates Instance ON DB
        entityManager.getTransaction().commit();

        entityManager.clear();
        Item itemVerifyPersist = entityManager.find(Item.class, itemPersist.getId());

        Assert.assertNotNull(itemVerifyPersist);

        Item itemMerge = new Item();
        itemMerge.setCreateDate(LocalDateTime.now());
        itemMerge.setName("Notebook Dell");
        itemMerge.setDescription("The best for your company!");
        itemMerge.setPrice(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        Item itemMergeCopyInstance = entityManager.merge(itemMerge); // Makes a copy of this instance and stores on entityManager
        itemMergeCopyInstance.setName("Notebook Dell 3"); // This line have effect!
        itemMerge.setName("Notebook Dell 2"); // Does not have any effect
        entityManager.getTransaction().commit();

        entityManager.clear();
        Item itemVerifyMerge = entityManager.find(Item.class, itemMergeCopyInstance.getId());

        Assert.assertNotNull(itemVerifyMerge);
    }

    @Test
    public void updateManagedObject() {
        Item item = entityManager.find(Item.class, 1);

        entityManager.getTransaction().begin();
        item.setName("Kindle Paperwhite 2 Gen");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Item itemVerify = entityManager.find(Item.class, item.getId());
        Assert.assertEquals("Kindle Paperwhite 2 Gen", itemVerify.getName());
    }
}
