package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import lab.lhss.ecommerce.model.Item_;
import lab.lhss.ecommerce.model.OrderItem_;
import lab.lhss.ecommerce.model.Order_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class PathExpressionTests extends EntityManagerTest {

    @Test
    public void findOrderWithItemsOfSpecificId() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Join<Order, OrderItem> joinOrderItem = root.join(Order_.ITEMS);

        criteriaQuery.where(criteriaBuilder.equal(joinOrderItem.get(OrderItem_.ITEM).get(Item_.ID), 1));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

}
