package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import lab.lhss.ecommerce.model.Client_;
import lab.lhss.ecommerce.model.Item_;
import lab.lhss.ecommerce.model.Order_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
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

    @Test
    public void conditionalGreaterThanWithDate() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get(Order_.CREATE_DATE), LocalDateTime.now().minusDays(3)));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void conditionalBetween() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.between(root.get(Order_.total), new BigDecimal(500), new BigDecimal(2000)));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void logicalOperators() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get(Order_.STATUS), OrderStatus.WAITING),
                        criteriaBuilder.equal(root.get(Order_.STATUS), OrderStatus.PAID)
                ),
                criteriaBuilder.greaterThan(root.get(Order_.TOTAL), new BigDecimal(499))
        );

        // jpql = select o from Order o where (status = 'WAITING' or status = 'PAID') and total > 499;

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void orderBy() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);

        criteriaQuery.select(root);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Client_.NAME)));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Client> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void caseTests() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.multiselect(
               root.get(Order_.ID),
//                criteriaBuilder.selectCase(root.get(Order_.STATUS))
//                    .when(OrderStatus.PAID.toString(), "Was Paid!")
//                    .when(OrderStatus.WAITING.toString(), "Is Waiting!")
//                    .otherwise(root.get(Order_.STATUS))
                criteriaBuilder.selectCase(root.get(Order_.payment).type().as(String.class))
                    .when("bankslip","Was paid with Bankslip")
                    .when("creditcard", "Was paid with Creditcard")
                    .otherwise("Payment not identified.")
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> {
            System.out.println(arr[0] + ", " + arr[1]);
        });

    }

    @Test
    public void inExpressionWithIntegerList() {
        List<Integer> idList = Arrays.asList(1, 2, 3);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.select(root);

        criteriaQuery.where(root.get(Order_.ID).in(idList));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void inExpressionWithEntity() {

        Client client1 = entityManager.find(Client.class, 1);

        Client client2 = new Client();
        client2.setId(2);

        List<Client> clientList = Arrays.asList(client1, client2);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.select(root);

        criteriaQuery.where(root.get(Order_.CLIENT).in(clientList));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }
}
