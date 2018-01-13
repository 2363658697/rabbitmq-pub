package model5;


import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;  
  
/**  
 * 消息发送者 - 生产者  
 * @author jiaozi  
 *  
 */  
public class Pub {  
    /**  
     * 交换器名称  
     */  
    private static final String EXCHANGE_NAME = "student";  
    /**  
     * @param args  
     * @throws Exception  
     */  
    public static void main(String[] args) throws Exception {  
        //连接远程rabbit-server服务器  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //定义创建一个交换器 参数1 名称  参数2 交换器类型 参数3表示将交换器信息永久保存在服务器磁盘上 关闭rabbitmqserver也不会丢失  
        channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);  
        //同时发送5条消息  
        //第二个参数就是routingkey     
        channel.basicPublish(EXCHANGE_NAME, "1610.cherry.girl", MessageProperties.PERSISTENT_TEXT_PLAIN, "新同学：切瑞".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1610.qianqian.boy", MessageProperties.PERSISTENT_TEXT_PLAIN, "新同学：谦谦".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1611.jiaozi.boy", MessageProperties.PERSISTENT_TEXT_PLAIN, "新同学：饺子".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1701.john.boy", MessageProperties.PERSISTENT_TEXT_PLAIN, "新同学：冏安".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1701.alise.girl", MessageProperties.PERSISTENT_TEXT_PLAIN, "新同学：爱丽丝".getBytes("UTF-8"));  
        channel.close();  
        connection.close();  
    }  
  
}  
