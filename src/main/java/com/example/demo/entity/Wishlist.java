package com.example.demo.entity;
public class Wishlist{

    private Long wishlistId;
    private int customerId;
    private int productId;


    public Wishlist() {

    }

    public Wishlist(final Long wishlistId, final int customerId, final int productId) {

      this.wishlistId = wishlistId;
      this.customerId = customerId;
      this.productId = productId;

    }

    public Long getWishlistId() {
        return wishlistId;
    }
    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }
    

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

}
    

