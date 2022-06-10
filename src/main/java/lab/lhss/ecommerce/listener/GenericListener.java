package lab.lhss.ecommerce.listener;

import javax.persistence.PostLoad;

public class GenericListener {

    @PostLoad
    public void loadLog(Object obj) {
        System.out.println("Entity " + obj.getClass().getSimpleName() + " was loaded.");
    }

}
