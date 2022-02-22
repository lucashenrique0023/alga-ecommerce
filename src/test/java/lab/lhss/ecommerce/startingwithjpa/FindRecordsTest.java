package lab.lhss.ecommerce.startingwithjpa;

import lab.lhss.ecommerce.model.Product;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FindRecordsTest {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

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
