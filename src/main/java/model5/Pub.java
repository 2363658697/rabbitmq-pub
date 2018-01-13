package model5;


import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;  
  
/**  
 * ��Ϣ������ - ������  
 * @author jiaozi  
 *  
 */  
public class Pub {  
    /**  
     * ����������  
     */  
    private static final String EXCHANGE_NAME = "student";  
    /**  
     * @param args  
     * @throws Exception  
     */  
    public static void main(String[] args) throws Exception {  
        //����Զ��rabbit-server������  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //���崴��һ�������� ����1 ����  ����2 ���������� ����3��ʾ����������Ϣ���ñ����ڷ����������� �ر�rabbitmqserverҲ���ᶪʧ  
        channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);  
        //ͬʱ����5����Ϣ  
        //�ڶ�����������routingkey     
        channel.basicPublish(EXCHANGE_NAME, "1610.cherry.girl", MessageProperties.PERSISTENT_TEXT_PLAIN, "��ͬѧ������".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1610.qianqian.boy", MessageProperties.PERSISTENT_TEXT_PLAIN, "��ͬѧ��ǫǫ".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1611.jiaozi.boy", MessageProperties.PERSISTENT_TEXT_PLAIN, "��ͬѧ������".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1701.john.boy", MessageProperties.PERSISTENT_TEXT_PLAIN, "��ͬѧ���װ�".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "1701.alise.girl", MessageProperties.PERSISTENT_TEXT_PLAIN, "��ͬѧ������˿".getBytes("UTF-8"));  
        channel.close();  
        connection.close();  
    }  
  
}  
