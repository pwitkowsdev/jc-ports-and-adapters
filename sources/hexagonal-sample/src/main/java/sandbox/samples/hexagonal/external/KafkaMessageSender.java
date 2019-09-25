package sandbox.samples.hexagonal.external;

import org.springframework.stereotype.Component;

@Component
public class KafkaMessageSender {

  public void send(String topic, String content) {
    System.out.println("Send message on topic:" + topic);
  }
}
