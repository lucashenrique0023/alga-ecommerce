package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Category;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class Pagination extends EntityManagerTest {

    @Test
    public void pageResults() {
        String jpql = "select c from Category c order by c.name";

        TypedQuery<Category> typedQuery = entityManager.createQuery(jpql, Category.class);
        typedQuery.setFirstResult(2); // OFFSET
        typedQuery.setMaxResults(2); // LIMIT (Number of rows)
        List<Category> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }


}
