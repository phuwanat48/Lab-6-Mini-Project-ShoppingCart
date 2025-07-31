package lib.Discount;



public final class Product {
    private final String productId;
    private final String productName;
    private final double price;
    





    private void checkRep(){
        if (productId == null || productId.isBlank()) {
            throw new RuntimeException("RI violated: productId cannot be blank.");
        }
        if (productName == null || productName.isBlank()) {
            throw new RuntimeException("RI violated: productName cannot be blank.");
        }
        if (price < 0) {
            throw new RuntimeException("RI violated: price cannot be blank.");
        }
    }
    
    /**
     * สร้างอ็อปเจกต์ Product
     * @param productId รหัสสินค้า ห้ามเป็นคำว่าง
     * @param productName ชื่อสินค้า ห้ามเป็นค่าว่าง
     * @param price ราคาไม่ติดลบ
     */



public Product(String productId,String productName,double price){
        this.productId=productId;
        this.productName=productName;
        this.price = price;
        checkRep(); //ตรวจสอบความถูกต้องทุกครั้งที่สร้าง
    }

    public String getProductId(){
        return productId;
    }
     public String getProductName(){
        return productId;
    }
     public String getPrice(){
        return productId;
    }

    /**
     * เปรียบเทียบ Product สองชิ้นโดยใช้ productId
     * @param obj อ็อปเจกต์ที่ต้องการเปรียบเทียบ
     * @return ture หาก productId เหมือนกัน
     */

    public boolean  equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass()!=obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return productId.equals(product.productId);
    }
}