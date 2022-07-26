package lab.lhss.ecommerce.criteria;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.*;
import lab.lhss.ecommerce.model.BankSlipPayment_;
import lab.lhss.ecommerce.model.Client_;
import lab.lhss.ecommerce.model.Order_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class FunctionsCriteriaTests extends EntityManagerTest {


    @Test
    public void stringFunctions() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Client> root = criteriaQuery.from(Client.class);

        criteriaQuery.multiselect(root.get(Client_.NAME),
                criteriaBuilder.concat("Client Name: ", root.get(Client_.NAME)),
                criteriaBuilder.length(root.get(Client_.NAME)),
                criteriaBuilder.locate(root.get(Client_.NAME), "a"),
                criteriaBuilder.substring(root.get(Client_.NAME), 1, 2),
                criteriaBuilder.lower(root.get(Client_.NAME)),
                criteriaBuilder.upper(root.get(Client_.NAME)),
                criteriaBuilder.trim(root.get(Client_.NAME))
        );

        criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.substring(root.get(Client_.NAME), 1, 1), "A" ));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(obj -> System.out.println(obj[0]
                + "\nConcat: " + obj[1]
                + "\nLength: " + obj[2]
                + "\nLocate: " + obj[3]
                + "\nSubstring: " + obj[4]
                + "\nLower: " + obj[5]
                + "\nUpper: " + obj[6]
                + "\nTrim: |" + obj[7] + "|"
                + "\n------------------"
                ));

    }

    @Test
    public void dateFunctions() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Order> root = criteriaQuery.from(Order.class);


        Join<Order, Payment> joinPayment = root.join(Order_.PAYMENT);
        Join<Order, BankSlipPayment> joinBankSlip = criteriaBuilder.treat(joinPayment, BankSlipPayment.class);

        criteriaQuery.multiselect(
                root.get(Order_.ID),
                criteriaBuilder.currentDate(),
                criteriaBuilder.currentTime(),
                criteriaBuilder.currentTimestamp()
        );

        criteriaQuery.where(
                criteriaBuilder.between(criteriaBuilder.currentTimestamp(),
                        root.get(Order_.CREATE_DATE),
                        joinBankSlip.get(BankSlipPayment_.DUE_DATE)),
                criteriaBuilder.equal(root.get(Order_.STATUS), OrderStatus.WAITING)
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(obj -> {
            System.out.println("Order ID: " + obj[0]
            + "\nCurrent Date: " + obj[1]
            + "\nCurrent Time: " + obj[2]
            + "\nTimestamp: " + obj[3]);
        });
    }

}
