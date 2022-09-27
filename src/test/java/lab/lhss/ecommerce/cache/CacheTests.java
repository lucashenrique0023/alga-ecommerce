package lab.lhss.ecommerce.cache;

import lab.lhss.ecommerce.model.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
