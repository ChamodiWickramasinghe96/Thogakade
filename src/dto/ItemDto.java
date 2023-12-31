package dto;

public class ItemDto {

    String code;
    String desc;
    double unitPrice;
    int qty;

    public ItemDto(String code, String desc, double unitPrice, int qty) {
        this.code = code;
        this.desc = desc;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public ItemDto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                '}';
    }
}
