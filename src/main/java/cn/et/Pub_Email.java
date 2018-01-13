package cn.et;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Pub_Email {

    // 任务被发送到的队列的名称
    static String QUEUE_NAME = "EMAIL_QUEUE";

    public static void main(String[] args) throws Exception {

        Map<String, String> message = new HashMap<String, String>();

        message.put("setTo", "1960150996@qq.com");
        message.put("setSubject", "xx欢迎您的加入");
        message.put("setText", "您的注册码为565153");

        // 连接远程rabbit-server服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.80.130");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 定义创建一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息
        channel.basicPublish("", QUEUE_NAME, null, Utils.ObjectToByte(message)); // 注意发送和接受段相同字符集否则出现乱码
        channel.close();
        connection.close();

    }
}
