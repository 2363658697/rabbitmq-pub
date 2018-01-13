package cn.et;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Pub_Email {

    // ���񱻷��͵��Ķ��е�����
    static String QUEUE_NAME = "EMAIL_QUEUE";

    public static void main(String[] args) throws Exception {

        Map<String, String> message = new HashMap<String, String>();

        message.put("setTo", "1960150996@qq.com");
        message.put("setSubject", "xx��ӭ���ļ���");
        message.put("setText", "����ע����Ϊ565153");

        // ����Զ��rabbit-server������
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.80.130");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // ���崴��һ������
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // ������Ϣ
        channel.basicPublish("", QUEUE_NAME, null, Utils.ObjectToByte(message)); // ע�ⷢ�ͺͽ��ܶ���ͬ�ַ��������������
        channel.close();
        connection.close();

    }
}
