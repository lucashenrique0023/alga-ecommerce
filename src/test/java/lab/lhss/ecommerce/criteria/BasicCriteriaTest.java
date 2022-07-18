package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BasicCriteriaTest extends EntityManagerTest {

    @Test
    public void findById() {
        // String jpql = "select o.client from Order o where o.id = 1";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.select(root.get("client"));

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        Client client = typedQuery.getSingleResult();
        Assert.assertNotNull(client);
    }
}
