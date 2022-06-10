package lab.lhss.ecommerce.service;

import lab.lhss.ecommerce.model.Order;

public class InvoiceService {

    public void generate(Order order) {
        System.out.println("Generating invoice to order: " + order.getId() + ".");
    }

}
