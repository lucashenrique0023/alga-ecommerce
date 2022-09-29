package lab.lhss.ecommerce.cache;

import lab.lhss.ecommerce.model.Order;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public class CacheTests {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void tearDownAfterClass() { entityManagerFactory.close(); }

    @Test
    public void findFromCache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Querying from instance 1.");
        entityManager1.find(Order.class, 1);

        System.out.println("Querying from instance 2.");
        entityManager2.find(Order.class, 1);
    }

    @Test
    public void addEntityToCache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Querying from instance 1.");
        entityManager1.createQuery("select o from Order o", Order.class).getResultList();

        System.out.println("Querying from instance 2.");
        entityManager2.find(Order.class, 1);

    }

    @Test
    public void removeEntityFromCache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        Cache cache = entityManagerFactory.getCache();

        System.out.println("Querying from instance 1.");
        entityManager1.createQuery("select o from Order o", Order.class).getResultList();

        System.out.println("Removing Order 1 from Cache");
        // cache.evict(Order.class, 1);  // Remove Specific entity with ID from cache
        // cache.evict(Order.class); // Remove All Entries of Specific Entity from Cache
        cache.evictAll(); // Remove All Entries of all Entities from cache

        System.out.println("Querying from instance 2.");
        entityManager2.find(Order.class, 1);
        entityManager2.find(Order.class, 2);

    }

    @Test
    public void verifyEntryIsCached() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        Cache cache = entityManagerFactory.getCache();

        System.out.println("Querying from instance 1.");
        entityManager1.createQuery("select o from Order o", Order.class).getResultList();

        System.out.println("Removing Order 1 from Cache");
        cache.evict(Order.class, 1);  // Remove Specific entity with ID from cache

        Assert.assertFalse(cache.contains(Order.class, 1));
        Assert.assertTrue(cache.contains(Order.class, 2));
    }

    @Test
    public void controlCacheUsageDynamically() {
        // javax.persistence.cache.retrieveMode CacheRetrieveMode
        // javax.persistence.cache.storeMode CacheStoreMode

        System.out.println("Querying All Orders #################################");
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
//        entityManager1.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager1.createQuery("select o from Order o", Order.class)
                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.USE)
                .getResultList();

        System.out.println("Querying Order with ID equals to 2 #######################");
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        Map<String, Object> properties = new HashMap<>();
//        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        properties.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager2.find(Order.class, 2, properties);

        System.out.println("Querying All Orders AGAIN ###################################");
        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        entityManager3.createQuery("select o from Order o", Order.class).getResultList();
    }

}
