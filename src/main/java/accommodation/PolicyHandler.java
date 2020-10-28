package accommodation;

import accommodation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler {
    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryCompleted(@Payload DeliveryCompleted deliveryCompleted){
        // 배송 완료시, 배송상태 Update
        if(deliveryCompleted.isMe()){
            System.out.println("##### ");
            System.out.println("##### 배송 완료 저장  : " + deliveryCompleted.toJson());
            System.out.println("##### ");

            Delivery delivery = new Delivery();
            //delivery.setDeliveryNumber(deliveryCompleted.getDeliveryNumber());
            delivery.setDeliveryStatus(deliveryCompleted.getDeliveryStatus());
            delivery.setReservationNumber(deliveryCompleted.getReservationNumber());
            delivery.setDeliveryStatus(deliveryCompleted.getDeliveryStatus());
            delivery.setCustomerId(deliveryCompleted.getCustomerId());
            delivery.setCustomerName(deliveryCompleted.getCustomerName());
            delivery.setCustomerAddress(deliveryCompleted.getCustomerAddress());
            delivery.setCustomerContract(deliveryCompleted.getCustomerContract());

            deliveryRepository.save(delivery);
        }
    }
}
