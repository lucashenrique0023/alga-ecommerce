package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import lab.lhss.ecommerce.model.Category_;
import lab.lhss.ecommerce.model.Client_;
import lab.lhss.ecommerce.model.Item_;
import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.model.OrderItem_;
import lab.lhss.ecommerce.model.Order_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class GroupByCriteriaTests extends EntityManagerTest {


    @Test
    public void itemsByCategoryTest() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Category> root = criteriaQuery.from(Category.class);
        Join<Category, Item> joinItem = root.join(Category_.ITEMS, JoinType.LEFT);


        criteriaQuery.multiselect(
                root.get(Category_.NAME),
                criteriaBuilder.count(joinItem.get(Item_.id))
        );

        criteriaQuery.groupBy(root.get(Category_.NAME));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> {
            System.out.println("Category: " + arr[0] + " -- Count: " + arr[1]);
                }
        );
    }

    @Test
    public void totalSalesByCategory() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<OrderItem> root = criteriaQuery.from(OrderItem.class);
        Join<OrderItem, Item> joinItem = root.join(OrderItem_.ITEM);
        Join<Item, Category> joinCategory = joinItem.join(Item_.CATEGORIES);


        criteriaQuery.multiselect(
                joinCategory.get(Category_.NAME),
                criteriaBuilder.sum(root.get(OrderItem_.ITEM_PRICE))
        );

        criteriaQuery.groupBy(joinCategory.get(Category_.NAME));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> {
                    System.out.println("Category: " + arr[0] + " -- Total Price: " + arr[1]);
                }
        );
    }

    @Test
    public void totalSalesByClient() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Client> root = criteriaQuery.from(Client.class);
        Join<Client, Order> joinOrder = root.join(Client_.ORDERS);

        criteriaQuery.multiselect(
                root.get(Client_.NAME),
                criteriaBuilder.sum(joinOrder.get(Order_.TOTAL))
        );

        criteriaQuery.groupBy(root.get(Client_.NAME));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> {
                    System.out.println("Client: " + arr[0] + " -- Total Spent: " + arr[1]);
                }
        );
    }

    @Test
    public void totalSalesByClient2() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Client> root = criteriaQuery.from(Client.class);
        Join<Client, Order> joinOrder = root.join(Client_.ORDERS);
        Join<Order, OrderItem> joinOrderItem = joinOrder.join(Order_.ITEMS);

        criteriaQuery.multiselect(
                root.get(Client_.NAME),
                criteriaBuilder.sum(joinOrderItem.get(OrderItem_.ITEM_PRICE))
        );

        criteriaQuery.groupBy(root.get(Client_.NAME));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> {
                    System.out.println("Client: " + arr[0] + " -- Total Spent: " + arr[1]);
                }
        );
    }
}
