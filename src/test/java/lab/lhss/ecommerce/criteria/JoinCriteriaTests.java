package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    @Test
    public void joinWithONClause() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Join<Order, Payment> joinOrderPayment = root.join("payment");
        joinOrderPayment.on(criteriaBuilder.equal(joinOrderPayment.get("status"), PaymentStatus.RECEIVED));

        criteriaQuery.select(root);

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void leftJoin() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Join<Order, Payment> joinOrderPayment = root.join("payment", JoinType.LEFT);
        joinOrderPayment.on(criteriaBuilder.equal(joinOrderPayment.get("status"), PaymentStatus.RECEIVED));

        criteriaQuery.select(root);

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

    }

    @Test
    public void joinFetch() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        root.fetch("payment", JoinType.LEFT);
        root.fetch("invoice", JoinType.LEFT);
        Join<Order, Client> joinClient = (Join<Order, Client>) root.<Order, Client>fetch("client");
        //root.fetch("client");

        criteriaQuery.select(root);

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

}
