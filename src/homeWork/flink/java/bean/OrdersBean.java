package org.spiral.druid.bean;

import java.util.List;

/**
 * org.spiral.druid.bean.OrdersBean
 *
 * @author spiral
 * @since 2020/12/26 - 下午336
 */
public class OrdersBean {
    private Long ts;
    private String orderId;
    private String userId;
    private Integer orderStatusId;
    private String orderStatus;
    private Integer payModeId;
    private String payMode;
    private String payment;
    private List<ProductBean> products;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayModeId() {
        return payModeId;
    }

    public void setPayModeId(Integer payModeId) {
        this.payModeId = payModeId;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "org.spiral.druid.bean.OrdersBean{" + "ts=" + ts + ", orderId='" + orderId + '\'' + ", userId='" + userId + '\'' + ", orderStatusId=" + orderStatusId + ", orderStatus='" + orderStatus + '\'' + ", payModeId=" + payModeId + ", payMode='" + payMode + '\'' + ", payment='" + payment + '\'' + ", products=" + products + '}';
    }
}
