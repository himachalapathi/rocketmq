package hemadri.app2;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt; 


public class Consumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mahesh");
         
        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");
        
        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

         //   @Override
        	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context)
        	{	           
        		for(MessageExt ms : msgs){		               
        			System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(ms.getBody()));	       
        			}	   
        		// System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
        		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;	   
        		}	   
        	});
        //Launch the consumer instance.
        consumer.start(); 

        System.out.printf("Consumer Started.%n");
    }
}