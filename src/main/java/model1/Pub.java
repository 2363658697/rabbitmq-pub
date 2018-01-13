package model1;


import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
  
/**
 * 简单模式：生产者推送到队列的消息只能由一个消费者获取
 * @author 生产者
 */
public class Pub {  
   
     //队列名称  
    private final static String QUEUE_NAME = "hello"; 
    
    public static void main(String[] args) throws Exception {  
        //连接远程rabbit-server服务器  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //定义创建一个队列  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = "Hello World!";  
        //发送消息  
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8")); //注意发送和接受段相同字符集否则出现乱码  
        System.out.println(" [x] Sent '" + message + "'");  
        channel.close();  
        connection.close();  
    }  
  
}  