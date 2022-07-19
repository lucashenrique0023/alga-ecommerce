package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.dto.ItemDTO;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BasicCriteriaTest extends EntityManagerTest {

    @Test
    public void getAllItems() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(root);

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Item> list = typedQuery.getResultList();
        Assert.assertNotNull(list);
    }

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

    @Test
    public void projection() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.multiselect(root.get("id"), root.get("name"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> System.out.println("ID: " + arr[0] + " Name: " + arr[1]));
    }

    @Test
    public void projectionWithTuple() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(criteriaBuilder
                .tuple(root.get("id").alias("id"), root.get("name").alias("name"))
        );

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Tuple> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(t -> System.out.println("ID: " + t.get("id") + " Name: " + t.get("name")));
    }

    @Test
    public void projectionWithDTO() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemDTO> criteriaQuery = criteriaBuilder.createQuery(ItemDTO.class);
        Root<Item> root = criteriaQuery.from(Item.class);

        criteriaQuery.select(criteriaBuilder.construct(
                ItemDTO.class,
                root.get("id"),
                root.get("name")));

        TypedQuery<ItemDTO> typedQuery = entityManager.createQuery(criteriaQuery);
        List<ItemDTO> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(i -> System.out.println("ID: " + i.getId() + " Name: " + i.getName()));
    }
}
