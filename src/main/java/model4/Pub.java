package model4;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;  
  
/**  
 * 路由模式：生产者推送消息至交换器时添加一个key,消费者创建队列时也创建一个key，当两个key相同时从交换器中获取消息
 * @author 消息发送者 - 生产者  
 *  
 */  
public class Pub {  
    /**  
     * 交换器名称  
     */  
    private static final String EXCHANGE_NAME = "X";  
    
    public static void main(String[] args) throws Exception {  
        //连接远程rabbit-server服务器  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //定义创建一个交换器 参数1 名称  参数2 交换器类型 参数3表示将交换器信息永久保存在服务器磁盘上 关闭rabbitmqserver也不会丢失  
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);  
        //第二个参数就是routingkey     
        channel.basicPublish(EXCHANGE_NAME, "error", MessageProperties.PERSISTENT_TEXT_PLAIN, "这是错误信息".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "info", MessageProperties.PERSISTENT_TEXT_PLAIN, "这是程序运行信息".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "warning", MessageProperties.PERSISTENT_TEXT_PLAIN, "这是警告".getBytes("UTF-8"));  
          
        channel.close();  
        connection.close();  
    }  
  
}  