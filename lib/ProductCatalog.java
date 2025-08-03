package lib;

import java.util.ArrayList;

/**
 * คลาสทำหน้าที่เป็นแคตตาล็แกสินค้า (Repository)
 */

public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();

    // RI: products list is not null, contains no null element, and no duplicate
    // products.
    // AF: AF(products) = A catalog of all available products.

    private void checkRep() {
        if (products == null) {
            throw new RuntimeException("RI violated: product list cannot be null.");
        }
        // Check for duplicate products
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    throw new RuntimeException("RI violated: catalog contains duplicate products.");

                }
            }
        }
    }

    public ProductCatalog() {
        checkRep();
    }

    /**
     * เพื่มสินค้าเข้าสู่แคตตาล็อก
     * 
     * @param product สินค้าที่ต้องการเห็น
     */
    public void addProduct(Product product) {
        if (product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
    }

    /**
     * ค้นหาสินค้าจากรหัสสินค้า
     * 
     * @param productId รหัสสินค้าที่ต้องการค้นหา
     * @return อ็อบเจกต์ Product หากพบ หรือ null หากไม่พบ
     */

    public Product findById(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

}
