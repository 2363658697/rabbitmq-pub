package model2;


import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
  
/**  
 * 工作队列模式：生成者推送多条消息至队列，可以由多个消费者同时从队列中获取消息，一条消息只能被一个消费者获取
 * @author 生产者
 */  
public class Pub {  

    // 队列名称  
    private final static String QUEUE_NAME = "mywork";  
    
    public static void main(String[] args) throws Exception {  
        //连接远程rabbit-server服务器  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //定义创建一个队列  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = null;  
        //同时发送5条消息  
        for(int i=0;i<=5;i++){  
            message="发送第"+i+"消息";  
            System.out.println(message);
            //发送消息  
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));  
        }  
          
        channel.close();  
        connection.close();  
    }  
  
}  
