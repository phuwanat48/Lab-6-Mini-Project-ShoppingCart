package lib.Discount;

import java.util.ArrayList;

public class ProductCatalog {

    private ArrayList<Product> products = new ArrayList<>();

    /**
     * คลาสทำหน้าที่เป็นแคตตาล็อกสินค้า
     */

    //RI : product list is not null,contains no null elements,and no
    //AF: AF(products) = A catalog of all available products.
    private void checkRep() {
        if (products == null) {
            throw new RuntimeException("RI violated: products ");
        }
        //check for duplicate products
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    throw new RuntimeException("RI violated : c");
                }
            }
        }
    }

    public ProductCatalog() {
        checkRep();
    }
    /**
     * เพิ่มสินค้าใหม่เข้าสู่แคตตาล็อก
     *  @param product สินค้าที่ต้องการเพิ่ม
     */

     public void addProduct(Product product){
        if (product !=null && !products.contains(product)) {
            products.add(product);
        }
     }

     
}
