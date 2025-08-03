package lib;

import java.util.*;

/**
 * คลาสนี้แทนตะกร้าสินค้า จัดการรายการสินค้า ปริมาณ และการคำนวณราคารวม
 */

public class ShoppingCart {

    // RI: items ต้องไม่เป็น null และต้องไม่มีผลิตภัณฑ์ซ้ำกัน

    private final List<CartItem> items;
    private final PricingService pricingService;
    private final ProductCatalog productCatalog;

    private void checkRep() {

        // RI 1: items ต้องไม่เป็น null
        if (items == null) {
            throw new RuntimeException("Representation Invariant violated: 'items' list is null.");
        }

        // RI 2: ไม่มีผลิตภัณฑ์ซ้ำกันใน CartItem
        for (int i = 0; i < items.size(); i++) {
            CartItem currentItem = items.get(i);
            if (currentItem == null || currentItem.getProduct() == null) {
                throw new RuntimeException("Representation Invariant violated: CartItem or its Product is null.");
            }
            String currentProductId = currentItem.getProduct().getProductId();
            for (int j = i + 1; j < items.size(); j++) {
                CartItem compareItem = items.get(j);
                if (compareItem == null || compareItem.getProduct() == null) {
                    throw new RuntimeException("Representation Invariant violated: CartItem or its Product is null.");
                }
                String compareProductId = compareItem.getProduct().getProductId();

                if (currentProductId.equals(compareProductId)) {
                    throw new RuntimeException("Representation Invariant violated: Duplicate product ID '"
                            + currentProductId + "' found in cart.");
                }
            }
        }
    }

    /**
     * สร้างวัตถุ ShoppingCart ใหม่
     * 
     * @param pricingService บริการที่ใช้สำหรับการคำนวณราคา
     * @param productCatalog แค็ตตาล็อกที่ใช้ค้นหาผลิตภัณฑ์
     */

    public ShoppingCart(PricingService pricingService, ProductCatalog productCatalog) {
        this.items = new ArrayList<>();
        this.pricingService = pricingService;
        this.productCatalog = productCatalog;
        checkRep();
    }

    /**
     * เพิ่มผลิตภัณฑ์ลงในตะกร้า หรืออัปเดตปริมาณหากมีอยู่แล้ว
     * 
     * @param productId ID ของผลิตภัณฑ์ที่จะเพิ่ม
     * @param quantity  ปริมาณที่จะเพิ่ม
     */

    public void addItem(String productId, int quantity) {
        if (quantity > 0) {
            Product product = productCatalog.findById(productId);
            if (product != null) {
                boolean found = false;
                for (CartItem item : items) {
                    if (item.getProduct().getProductId().equals(productId)) {
                        item.increaseQuantity(quantity);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    items.add(new CartItem(product, quantity));
                }
            }
        }
        checkRep();
    }

    /**
     * ลบผลิตภัณฑ์ทั้งหมดออกจากตะกร้า
     * 
     * @param productId ID ของผลิตภัณฑ์ที่จะลบ
     */
    public void removeItem(String productId) {
        for (int i = items.size() - 1; i >= 0; i--) {
            CartItem item = items.get(i);
            if (item.getProduct().getProductId().equals(productId)) {
                items.remove(i);
                break;
            }
        }
        checkRep();
    }

    /**
     * คืนค่าจำนวนสินค้าที่ไม่ซ้ำกัน (ผลิตภัณฑ์ที่แตกต่างกัน) ในตะกร้า
     * 
     * @return The count of unique items.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * คำนวณราคารวมของสินค้าทั้งหมดในตะกร้า โดยใช้ส่วนลด
     * 
     * @return The total price.
     */
    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += pricingService.calculateItemPrice(item);
        }
        return total;
    }

    /**
     * ลบสินค้าทั้งหมดออกจากตะกร้าสินค้า
     */
    public void clearCart() {
        items.clear();
        checkRep();
    }
}
