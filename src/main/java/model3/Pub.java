package model3;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;  
  
/**  
 * ��������ģʽ�������ڹ㲥������Ϣ�������˹㲥�Ķ����յ���Ϣ
 * @author ��Ϣ������ - ������  
 */  
public class Pub {  
    /**  
     * ����������  
     */  
    private static final String EXCHANGE_NAME = "logs";  
    
    public static void main(String[] args) throws Exception {  
        //����Զ��rabbit-server������  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //���崴��һ�������� ����1 ����  ����2 ���������� ����3��ʾ����������Ϣ���ñ����ڷ����������� �ر�rabbitmqserverҲ���ᶪʧ  
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);  
        String message = null;  
        //ͬʱ����5����Ϣ  
        for(int i=0;i<=5;i++){  
            message="���͵�"+i+"��Ϣ";  
            //�ڶ�����������routingkey  ���� Ĭ�ϻ�ת�������еĶ����߶���  
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));  
        }  
          
        System.out.println(" [x] Sent 6 message");  
        channel.close();  
        connection.close();  
    }  
}  