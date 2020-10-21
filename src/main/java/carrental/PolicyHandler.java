package carrental;

import carrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    CarManagementRepository carManagementRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_Rental(@Payload Paid paid){

        if(paid.isMe()){
            System.out.println("##### listener Rental : " + paid.toJson());

            List<CarManagement> optional = carManagementRepository.findByCarNo(paid.getCarNo());
            for(CarManagement carManagement : optional) {
                carManagement.setStatus("RENTED");
                carManagementRepository.save(carManagement);
            }
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaidCanceled_RentCancel(@Payload PaidCanceled paidCanceled){

        if(paidCanceled.isMe()){
            System.out.println("##### listener RentCancel : " + paidCanceled.toJson());

            List<CarManagement> optional = carManagementRepository.findByCarNo(paidCanceled.getCarNo());
            for(CarManagement carManagement : optional) {
                carManagement.setStatus("WAITING");
                carManagementRepository.save(carManagement);
            }
        }
    }

}
