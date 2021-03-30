package org.spiral.druid.bean;

public class ProductBean {
    private String productId;
    private String productName;
    private Double price;
    private Integer productNum;
    private String categoryid;
    private String catname1;
    private String catname2;
    private String catname3;

    public ProductBean() {
    }

    public ProductBean(String productId, String productName, Double price,
            Integer productNum, String categoryid, String catname1,
            String catname2, String catname3) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productNum = productNum;
        this.categoryid = categoryid;
        this.catname1 = catname1;
        this.catname2 = catname2;
        this.catname3 = catname3;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCatname1() {
        return catname1;
    }

    public void setCatname1(String catname1) {
        this.catname1 = catname1;
    }

    public String getCatname2() {
        return catname2;
    }

    public void setCatname2(String catname2) {
        this.catname2 = catname2;
    }

    public String getCatname3() {
        return catname3;
    }

    public void setCatname3(String catname3) {
        this.catname3 = catname3;
    }

    @Override
    public String toString() {
        return "org.spiral.druid.bean.ProductBean{" + "productId='" + productId + '\'' + ", productName='" + productName + '\'' + ", price=" + price + ", productNum=" + productNum + ", categoryid='" + categoryid + '\'' + ", catname1='" + catname1 + '\'' + ", catname2='" + catname2 + '\'' + ", catname3='" + catname3 + '\'' + '}';
    }
}
