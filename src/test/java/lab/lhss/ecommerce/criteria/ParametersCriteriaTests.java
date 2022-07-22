package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Invoice;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParametersCriteriaTests extends EntityManagerTest {

    @Test
    public void dateParameter() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteriaQuery = criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> root = criteriaQuery.from(Invoice.class);

        criteriaQuery.select(root);

        ParameterExpression<Date> parameterExpressionData = criteriaBuilder
                .parameter(Date.class, "startDate");

        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("emissionDate"), parameterExpressionData));

        TypedQuery<Invoice> typedQuery = entityManager.createQuery(criteriaQuery);

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -30);

        typedQuery.setParameter("startDate", startDate.getTime(), TemporalType.TIMESTAMP);

        List<Invoice> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void integerParameter() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.select(root);

        ParameterExpression<Integer> parameterExpressionId = criteriaBuilder
                .parameter(Integer.class, "id");

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), parameterExpressionId));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("id", 1);

        Order order = typedQuery.getSingleResult();
        Assert.assertNotNull(order);
    }
}
