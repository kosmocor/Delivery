package accommodation;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;


@Entity
@Table(name="Payment_table")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer deliveryNumber;
    private String deliveryStatus;

    // 예약 정보
    private Integer reservationNumber;
    // 고객 정보
    private Integer customerId;
    private String customerName;
    private String customerAddress;
    private String customerContract;

    @PrePersist
    public void onPrePersist(){

        // 배송 추가
        // -> DeliveryRequested 이면, Delay 만
        if ("DeliveryRequested".equals(deliveryStatus) ) {
            System.out.println("=============배송 요청 진행=============");
            System.out.println("======= 1. deliveryNumber    = " + deliveryNumber);
            System.out.println("======= 2. deliveryStatus    = " + deliveryStatus);
            System.out.println("======= 3. reservationNumber = " + reservationNumber);
            System.out.println("======= 4. customerId        = " + customerId);
            System.out.println("======= 5. customerName      = " + customerName);
            System.out.println("======= 6. customerAddress   = " + customerAddress);
            System.out.println("======= 7. customerContract  = " + customerContract);
            /*
            DeliveryRequested deliveryRequested = new DeliveryRequested();

            deliveryRequested.setDeliveryNumber(deliveryNumber);
            deliveryRequested.setDeliveryStatus(deliveryStatus);
            deliveryRequested.setReservationNumber(reservationNumber);
            deliveryRequested.setCustomerId(customerId);
            deliveryRequested.setCustomerName(customerName);
            deliveryRequested.setCustomerAddress(customerAddress);
            deliveryRequested.setCustomerContract(customerContract);

            BeanUtils.copyProperties(this, deliveryRequested);
            deliveryRequested.publishAfterCommit();
             */

            long delayed = (long) (400 + Math.random() * 300);
            try {
                //Thread.currentThread().sleep((long) (400 + Math.random() * 300));
                Thread.currentThread().sleep((long) delayed);

                System.out.println("======= finally delayed : " + delayed);
                System.out.println("=============배송 요청 수신 완료=============");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(Integer deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Integer getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Integer reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContract() {
        return customerContract;
    }

    public void setCustomerContract(String customerContract) {
        this.customerContract = customerContract;
    }
}




