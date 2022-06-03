package lab.lhss.ecommerce.relations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SelfRelationCategoriesTests extends EntityManagerTest {

    @Test
    public void verifyRelation() {

        Category upperCategory = new Category();
        upperCategory.setName("Eletronics");

        Category lowerCategory = new Category();
        lowerCategory.setName("Cellphone");
        lowerCategory.setUpperCategory(upperCategory);

        entityManager.getTransaction().begin();
        entityManager.persist(upperCategory);
        entityManager.persist(lowerCategory);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Category lowerCategoryVerification = entityManager.find(Category.class, lowerCategory.getId());
        Assert.assertNotNull(lowerCategoryVerification.getUpperCategory());
        Assert.assertEquals(lowerCategoryVerification.getUpperCategory().getId(), upperCategory.getId());

        Category upperCategoryVerification = entityManager.find(Category.class, upperCategory.getId());
        Assert.assertFalse(upperCategoryVerification.getLowerCategories().isEmpty());
    }
}
