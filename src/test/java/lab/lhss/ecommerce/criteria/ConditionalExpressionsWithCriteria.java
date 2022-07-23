package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Item_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class ConditionalExpressionsWithCriteria extends EntityManagerTest {


    @Test
    public void conditionalLike() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get(Item_.NAME), "%a%"));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void conditionalIsNull() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.isNull(root.get(Item_.picture)));
        //criteriaQuery.where(root.get(Item_.picture).isNull());

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void conditionalIsEmpty() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.isEmpty(root.get(Item_.categories)));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void conditionalGreaterThan() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.greaterThan(root.get(Item_.price), new BigDecimal(1500)));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

}
