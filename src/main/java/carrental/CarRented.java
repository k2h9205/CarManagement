package carrental;

public class CarRented extends AbstractEvent {

    private Long id;
    private Long carNo;
    private String status;

    public CarRented(){
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
}
