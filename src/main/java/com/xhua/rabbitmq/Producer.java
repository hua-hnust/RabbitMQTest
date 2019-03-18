package com.xhua.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2019/3/12.
 */
public class Producer {
    public final  static String QUEUE_NAME = "rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("root");
        factory.setPassword("xhua");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        //创建新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String message = "Hello RabbitMQ!";
        //发送消息到队列中
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));

        channel.close();
        connection.close();
    }
}
