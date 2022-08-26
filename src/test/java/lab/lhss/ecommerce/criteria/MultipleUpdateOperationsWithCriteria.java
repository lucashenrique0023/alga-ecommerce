package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Category;
import lab.lhss.ecommerce.model.Category_;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Item_;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;

public class MultipleUpdateOperationsWithCriteria extends EntityManagerTest {

    @Test
    public void multipleUpdate() {
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Item> criteriaUpdate = builder.createCriteriaUpdate(Item.class);
        Root<Item> root = criteriaUpdate.from(Item.class);

        criteriaUpdate.set(root.get(Item_.price),
                builder.prod(root.get(Item_.price), new BigDecimal("1.1")));

        Subquery<Integer> subquery = criteriaUpdate.subquery(Integer.class);
        Root<Item> subqueryRoot = subquery.correlate(root);
        Join<Item, Category> joinCategory = subqueryRoot.join(Item_.categories);

        subquery.select(builder.literal(1)).where(builder.equal(joinCategory.get(Category_.id), 2));

        criteriaUpdate.where(builder.exists(subquery));

        Query query = entityManager.createQuery(criteriaUpdate);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

    @Test
    public void multipleDelete() {
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Item> criteriaDelete = builder.createCriteriaDelete(Item.class);
        Root<Item> root = criteriaDelete.from(Item.class);

        criteriaDelete.where(builder.between(root.get(Item_.id), 6,9));

        Query query = entityManager.createQuery(criteriaDelete);
        query.executeUpdate();

        entityManager.getTransaction().commit();

    }

}



