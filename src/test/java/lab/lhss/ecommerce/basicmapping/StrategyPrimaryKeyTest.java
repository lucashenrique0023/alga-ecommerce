package lab.lhss.ecommerce.basicmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Category;
import org.junit.Assert;
import org.junit.Test;

public class StrategyPrimaryKeyTest extends EntityManagerTest {

    @Test
    public void testStrategyKey() {
        Category category = new Category();
        category.setName("Electronics");

        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Category categoryVerify = entityManager.find(Category.class, category.getId());
        Assert.assertNotNull(categoryVerify);
    }
}
