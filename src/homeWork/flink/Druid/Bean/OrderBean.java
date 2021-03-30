package org.spiral.druid.bean;

public class OrderBean {
    private Long ts;
    private String orderId;
    private String userId;
    private Integer orderStatusId;
    private String orderStatus;
    private Integer payModeId;
    private String payMode;
    private String payment;
    private ProductBean product;

    public OrderBean() {
    }

    public OrderBean(Long ts, String orderId, String userId,
            Integer orderStatusId, String orderStatus, Integer payModeId,
            String payMode, String payment, ProductBean product) {
        this.ts = ts;
        this.orderId = orderId;
        this.userId = userId;
        this.orderStatusId = orderStatusId;
        this.orderStatus = orderStatus;
        this.payModeId = payModeId;
        this.payMode = payMode;
        this.payment = payment;
        this.product = product;
    }

    /**
     * 根据OrdersBean和ProductBean生成OrderBean
     *
     * @param orders  多product的订单信息
     * @param product product信息
     * @return OrderBean
     */
    public static OrderBean of(OrdersBean orders, ProductBean product) {
        return new OrderBean(orders.getTs(), orders.getOrderId(),
                orders.getUserId(), orders.getOrderStatusId(),
                orders.getOrderStatus(), orders.getPayModeId(),
                orders.getPayMode(), orders.getPayment(), product);
    }

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

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "org.spiral.druid.bean.OrderBean{" + "ts=" + ts + ", orderId='" + orderId + '\'' + ", userId='" + userId + '\'' + ", orderStatusId=" + orderStatusId + ", orderStatus='" + orderStatus + '\'' + ", payModeId=" + payModeId + ", payMode='" + payMode + '\'' + ", payment='" + payment + '\'' + ", product=" + product + '}';
    }
}
