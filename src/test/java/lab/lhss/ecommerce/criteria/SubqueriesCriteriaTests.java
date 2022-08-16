package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import lab.lhss.ecommerce.model.Category_;
import lab.lhss.ecommerce.model.Client_;
import lab.lhss.ecommerce.model.Item_;
import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.model.OrderItemId_;
import lab.lhss.ecommerce.model.OrderItem_;
import lab.lhss.ecommerce.model.Order_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

public class SubqueriesCriteriaTests extends EntityManagerTest {

    @Test
    public void selectItemsWithHighestPrice() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = builder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Item> subqueryRoot = subquery.from(Item.class);
        subquery.select(builder.max(subqueryRoot.get(Item_.PRICE)));

        criteriaQuery.where(builder.equal(root.get(Item_.price), subquery));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(i -> System.out.println("Item: " + i.getName() + " Price: " + i.getPrice()));
    }

    @Test
    public void selectAllOrdersWithTotalAboveSalesAverage() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        // Subquery get average of orders total
        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Order> subqueryRoot = subquery.from(Order.class);
        subquery.select(builder.avg(subqueryRoot.get(Order_.TOTAL)).as(BigDecimal.class));

        criteriaQuery.select(root);
        criteriaQuery.where(builder.greaterThan(root.get(Order_.TOTAL), subquery));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(o -> System.out.println("Order: " + o.getId() + " Total: " + o.getTotal()));
    }

    @Test
    public void relationSubqueryWithMainQuery() {
        // Query Example:
        // select c from Client c where (select o.total from Order o where o.client = c) > 500;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subQuery = criteriaQuery.subquery(BigDecimal.class);
        Root<Order> subqueryRoot = subQuery.from(Order.class);
        subQuery.select(criteriaBuilder.sum(subqueryRoot.get(Order_.TOTAL)));
        subQuery.where(criteriaBuilder.equal(root, subqueryRoot.get(Order_.CLIENT))); // relation

        criteriaQuery.where(criteriaBuilder.greaterThan(subQuery, new BigDecimal(5500)));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Client> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(c -> System.out.println("Client Name: " + c.getName()));
    }

    @Test
    public void subqueryWithConditionalIN() {

        // Query Example:
        // select o from Order o where o.id in
        // (select o2.id from OrderItem oi2
        //  join oi2.order o2
        //  join oi2.item it2
        //  where it2.price > 100);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<OrderItem> subqueryRoot = subquery.from(OrderItem.class);
        Join<OrderItem, Order> joinOrder = subqueryRoot.join(OrderItem_.ORDER);
        Join<OrderItem, Item> joinItem = subqueryRoot.join(OrderItem_.ITEM);

        subquery.select(joinOrder.get(Order_.ID));
        subquery.where(criteriaBuilder.greaterThan(joinItem.get(Item_.PRICE), 3400));

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.in(root.get(Client_.ID)).value(subquery));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(o -> System.out.println("Order Id: " + o.getId()));
    }

    @Test
    public void subqueryWithConditionalExists() {

        // Query Example:
        // select i from Item i where exists
        // ( select 1 from OrderItem oi2 join oi2.item i2 where i2 = i );

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<OrderItem> subqueryRoot = subquery.from(OrderItem.class);
        Join<OrderItem, Item> joinItem = subqueryRoot.join(OrderItem_.ITEM);
        subquery.select(criteriaBuilder.literal(1));
        subquery.where(criteriaBuilder.equal(joinItem, root));

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.exists(subquery));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(i -> System.out.println("Item Id: " + i.getId() + ", Name: " + i.getName()));
    }

    @Test
    public void getClientsWithMoreThan2OrdersWithSubqueryAndExists() {

        // select * from Client c where exists
        // (select 1 from order o where o.client_id = c.id group by client_id having count(*) > 2);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<Order> subqueryRoot = subquery.from(Order.class);

        subquery.select(criteriaBuilder.literal(1))
                .where(criteriaBuilder.equal(root, subqueryRoot.get(Order_.CLIENT)))
                .groupBy(subqueryRoot.get(Order_.CLIENT)).having(
                criteriaBuilder.greaterThan(criteriaBuilder.count(subqueryRoot.get(Order_.CLIENT)).as(Integer.class), 2));


        criteriaQuery.select(root).where(criteriaBuilder.exists(subquery));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Client> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(c -> System.out.println("Client Id: " + c.getId()));

    }

    @Test
    public void getAllOrdersThatContainsItemsWithSpecificCategory() {

        // select * from orderr o where exists
        //( select 1 from order_item oi
        //	join item i on oi.item_id = i.id
        //    join item_category ic on ic.item_id = i.id and ic.category_id = 8
        //    where oi.order_id = o.id );

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<OrderItem> subqueryRoot = subquery.from(OrderItem.class);
        Join<OrderItem, Item> joinItem = subqueryRoot.join(OrderItem_.ITEM);
        Join<Item, Category> joinCategory = joinItem.join(Item_.CATEGORIES);

        subquery.select(subqueryRoot.get(OrderItem_.id).get(OrderItemId_.orderId))
                .where(criteriaBuilder.equal(joinCategory.get(Category_.id), 2));

        criteriaQuery.select(root).where(root.get(Order_.ID).in(subquery));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(o -> System.out.println("Order ID: " + o.getId()));

    }


    @Test
    public void getSoldItemsWithDifferentActualPriceUsingExists() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<OrderItem> subqueryRoot = subquery.from(OrderItem.class);
        Join<OrderItem, Item> joinItem = subqueryRoot.join(OrderItem_.ITEM);

        subquery.select(criteriaBuilder.literal(1));
        subquery.where(
                criteriaBuilder.notEqual(subqueryRoot.get(OrderItem_.itemPrice), joinItem.get(Item_.PRICE)),
                criteriaBuilder.equal(joinItem.get(Item_.ID), root.get(Item_.ID)));


        criteriaQuery.select(root).where(criteriaBuilder.exists(subquery));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(i -> System.out.println("Item ID: " + i.getId()));

    }

    @Test
    public void getAllProductsThatHasBeenSoldAlwaysForSamePriceWithAll() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        Subquery<BigDecimal> subGetAllPricesOfSalesForThisItem = criteriaQuery.subquery(BigDecimal.class);
        Root<OrderItem> subqueryRoot = subGetAllPricesOfSalesForThisItem.from(OrderItem.class);
        subGetAllPricesOfSalesForThisItem.select(subqueryRoot.get(OrderItem_.itemPrice))
            .where(criteriaBuilder.equal(root, subqueryRoot.get(OrderItem_.item)));

        Subquery<Integer> subExistsSalesForThisItem = criteriaQuery.subquery(Integer.class);
        Root<OrderItem> subquery2Root = subExistsSalesForThisItem.from(OrderItem.class);
        subExistsSalesForThisItem.select(criteriaBuilder.literal(1))
                .where(criteriaBuilder.equal(subquery2Root.get(OrderItem_.item), root));

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(Item_.price), criteriaBuilder.all(subGetAllPricesOfSalesForThisItem)),
                        criteriaBuilder.exists(subExistsSalesForThisItem));

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(i -> System.out.println("Item Id: " + i.getId()));
    }

}
