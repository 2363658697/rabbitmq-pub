package model3;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;  
  
/**  
 * 发布订阅模式：类似于广播推送消息，收听了广播的都能收到消息
 * @author 消息发送者 - 生产者  
 */  
public class Pub {  
    /**  
     * 交换器名称  
     */  
    private static final String EXCHANGE_NAME = "logs";  
    
    public static void main(String[] args) throws Exception {  
        //连接远程rabbit-server服务器  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //定义创建一个交换器 参数1 名称  参数2 交换器类型 参数3表示将交换器信息永久保存在服务器磁盘上 关闭rabbitmqserver也不会丢失  
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);  
        String message = null;  
        //同时发送5条消息  
        for(int i=0;i<=5;i++){  
            message="发送第"+i+"消息";  
            //第二个参数就是routingkey  不填 默认会转发给所有的订阅者队列  
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));  
        }  
          
        System.out.println(" [x] Sent 6 message");  
        channel.close();  
        connection.close();  
    }  
}  