package bo;

import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getInstance(){
        return boFactory==null? (boFactory=new BoFactory()) : boFactory;
    }

    public enum BoTypes{
        CUSTOMER,ITEM
    }

    public <T> T getBo(BoTypes types){
        switch (types){
            case CUSTOMER:
                return (T)new CustomerBoImpl();
            case ITEM:
                return  (T)new ItemBoImpl();
            default:
                return null;
        }
    }
}
