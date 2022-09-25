package lab.lhss.ecommerce.entitygraph;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.model.Order_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityGraphTests extends EntityManagerTest {

    @Test
    public void findEssentialAttributesFromOrderWithSubGraph() {

        EntityGraph<Order> entityGraph = entityManager.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes(
                Order_.CREATE_DATE, Order_.STATUS, Order_.TOTAL, Order_.INVOICE
        );

        Subgraph<Client> subgraphClient = entityGraph.addSubgraph("client", Client.class);
        subgraphClient.addAttributeNodes("name", "cpf");

        TypedQuery<Order> typedQuery = entityManager.createQuery("select o from Order o", Order.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());


    }

    @Test
    public void findEssentialAttributesFromOrder() {

        EntityGraph<Order> entityGraph = entityManager.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes(
                Order_.CREATE_DATE, Order_.STATUS, Order_.TOTAL, Order_.INVOICE
        );

//        Map<String, Object> properties = new HashMap<>();
//        properties.put("javax.persistence.fetchgraph", entityGraph);
//        properties.put("javax.persistence.loadgraph", entityGraph);
//        Order order = entityManager.find(Order.class, 1, properties);
//        Assert.assertNotNull(order);

        TypedQuery<Order> typedQuery = entityManager.createQuery("select o from Order o", Order.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());


    }

}
