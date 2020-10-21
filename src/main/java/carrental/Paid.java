package carrental;

public class Paid extends AbstractEvent {

    private Long id;
    private Long carNo;
    private String status;
    private String paymentDate;

    public Paid(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCarNo() {
        return carNo;
    }

    public void setCarNo(Long carNo) {
        this.carNo = carNo;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
