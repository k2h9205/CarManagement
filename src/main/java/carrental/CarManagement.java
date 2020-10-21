package carrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="CarManagement_table")
public class CarManagement {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long carNo;
    private Double carPrice;
    private String status;

    @PrePersist
    public void onPostPersist(){
        System.out.println("##### onPostPersist status = " + this.getStatus());

        // 차량 등록(상태 WAITING)-> carRegistered
        if(this.getStatus().equals("WAITING")) {
            CarRegistered carRegistered = new CarRegistered();
            BeanUtils.copyProperties(this, carRegistered);
            carRegistered.publishAfterCommit();
        }
    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("##### onPostUpdate status = " + this.getStatus());

        // PAID-> carRented, PAYMENT_CANCELED-> carRentCanceled
        if(this.getStatus().equals("RENTED")) {
            CarRented carRented = new CarRented();
            BeanUtils.copyProperties(this, carRented);
            carRented.publishAfterCommit();
        } else {
            CarRentCanceled carRentCanceled = new CarRentCanceled();
            BeanUtils.copyProperties(this, carRentCanceled);
            carRentCanceled.publishAfterCommit();
        }
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
    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
