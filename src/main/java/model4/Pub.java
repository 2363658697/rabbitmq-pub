package model4;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;  
  
/**  
 * ·��ģʽ��������������Ϣ��������ʱ���һ��key,�����ߴ�������ʱҲ����һ��key��������key��ͬʱ�ӽ������л�ȡ��Ϣ
 * @author ��Ϣ������ - ������  
 *  
 */  
public class Pub {  
    /**  
     * ����������  
     */  
    private static final String EXCHANGE_NAME = "X";  
    
    public static void main(String[] args) throws Exception {  
        //����Զ��rabbit-server������  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //���崴��һ�������� ����1 ����  ����2 ���������� ����3��ʾ����������Ϣ���ñ����ڷ����������� �ر�rabbitmqserverҲ���ᶪʧ  
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);  
        //�ڶ�����������routingkey     
        channel.basicPublish(EXCHANGE_NAME, "error", MessageProperties.PERSISTENT_TEXT_PLAIN, "���Ǵ�����Ϣ".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "info", MessageProperties.PERSISTENT_TEXT_PLAIN, "���ǳ���������Ϣ".getBytes("UTF-8"));  
        channel.basicPublish(EXCHANGE_NAME, "warning", MessageProperties.PERSISTENT_TEXT_PLAIN, "���Ǿ���".getBytes("UTF-8"));  
          
        channel.close();  
        connection.close();  
    }  
  
}  