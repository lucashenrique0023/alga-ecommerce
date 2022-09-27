package lab.lhss.ecommerce.cache;

import lab.lhss.ecommerce.model.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

}
