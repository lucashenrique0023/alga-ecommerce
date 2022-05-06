package lab.lhss.ecommerce.basicmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Category;
import org.junit.Assert;
import org.junit.Test;

public class StrategyPrimaryKeyTest extends EntityManagerTest {

    @Test
    public void testStrategyAuto() {
        Category category = new Category();
        category.setName("Electronics");

        Category category2 = new Category();
        category2.setName("Electronics");

        Category category3 = new Category();
        category3.setName("Electronics");

        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.persist(category2);
        entityManager.persist(category3);
        entityManager.getTransaction().commit();



        entityManager.clear();

        Category categoryVerify = entityManager.find(Category.class, category.getId());
        Assert.assertNotNull(categoryVerify);
    }
}
