package model2;


import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
  
/**  
 * ��������ģʽ�����������Ͷ�����Ϣ�����У������ɶ��������ͬʱ�Ӷ����л�ȡ��Ϣ��һ����Ϣֻ�ܱ�һ�������߻�ȡ
 * @author ������
 */  
public class Pub {  

    // ��������  
    private final static String QUEUE_NAME = "mywork";  
    
    public static void main(String[] args) throws Exception {  
        //����Զ��rabbit-server������  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.80.130");  
        factory.setPort(5672);  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //���崴��һ������  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = null;  
        //ͬʱ����5����Ϣ  
        for(int i=0;i<=5;i++){  
            message="���͵�"+i+"��Ϣ";  
            System.out.println(message);
            //������Ϣ  
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));  
        }  
          
        channel.close();  
        connection.close();  
    }  
  
}  
