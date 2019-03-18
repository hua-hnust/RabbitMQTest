package com.xhua.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2019/3/12.
 */
public class NewTask {

    private static final String TASK_QUEUE_NAME="task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("root");
        factory.setPassword("xhua");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel =connection.createChannel();
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);

        for (int i=0;i<20;i++){
            String message = "Hello RabbitMQ"+i;
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

            System.out.println("NewTask send '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
