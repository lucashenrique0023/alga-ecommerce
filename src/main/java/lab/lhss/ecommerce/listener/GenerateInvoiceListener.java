package lab.lhss.ecommerce.listener;

import lab.lhss.ecommerce.model.Order;
import lab.lhss.ecommerce.service.InvoiceService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GenerateInvoiceListener {

    private final InvoiceService invoiceService = new InvoiceService();

    @PrePersist
    public void prePersist(Order order){
        System.out.println("PrePersist Running!!");
        this.generate(order);
    }

    @PreUpdate
    public void preUpdate(Order order) {
        System.out.println("PreUpdate Running!!");
        this.generate(order);
    }

    private void generate(Order order) {
        if (order.isPaid() && order.getInvoice() == null) {
            invoiceService.generate(order);
        }
    }
}
