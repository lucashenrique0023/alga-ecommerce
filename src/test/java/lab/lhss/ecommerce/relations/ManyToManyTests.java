package lab.lhss.ecommerce.relations;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Category;
import lab.lhss.ecommerce.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class ManyToManyTests extends EntityManagerTest {

    @Test
    public void verifyRelation() {
        Item item = entityManager.find(Item.class, 1);
        Category category = entityManager.find(Category.class, 1);

        entityManager.getTransaction().begin();
        //category.setItens(Collections.singletonList(item)); // Relation NO Owner, Do not persist!
        item.setCategories(Collections.singletonList(category)); // Relation Owner, Persist OK!
        entityManager.getTransaction().commit();

        entityManager.clear();
        Category categoryVerify = entityManager.find(Category.class, 1);
        Assert.assertFalse(categoryVerify.getItens().isEmpty());
    }

}
