package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class JoinCriteriaTests extends EntityManagerTest {

    @Test
    public void innerJoin() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Join<Order, Payment> joinPayment = root.join("payment");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(joinPayment.get("status"), PaymentStatus.PROCESSING));


        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void multipleInnerJoin() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Join<Order, OrderItem> joinOrderOrderItem = root.join("items");
        Join<OrderItem, Item> joinOrderItemItem = joinOrderOrderItem.join("item");

        criteriaQuery.select(joinOrderItemItem);
        criteriaQuery.where(criteriaBuilder.equal(joinOrderItemItem.get("id"), 1));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        Item item = typedQuery.getSingleResult();
        System.out.println(item.getName());
    }

}
