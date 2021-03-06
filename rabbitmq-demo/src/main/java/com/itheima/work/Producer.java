package com.itheima.work;

import com.itheima.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 工作模式
 * @author ljh
 * @version 1.0
 * @date 2020/12/15 10:47
 * @description 标题
 * @package com.itheima.work
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        Channel channel = connection.createChannel();
        //声明队列(创建队列)
        //参数1 指定队列的名称
        //参数2 指定是消息否持久化  一般设置为true 要保存到磁盘中
        //参数3 指定是否独占通道  不独占
        //参数4 指定是否自动删除 不要自动删除。
        //参数5 指定额外的参数 可以不指定
        channel.queueDeclare("simple_queue2", true, false, false, null);

        for (int i = 0; i < 20; i++) {
            //创建消息
            String msg = "hello i am from simple producer "+i;

            //消息发送
            //参数1 指定交换机 简单模式中使用默认的交换机 指定空字符串
            //参数2 指定routingkey  简单模式 只需要指定队列名称即可
            //参数3 指定携带的额外的参数 null
            //参数4 要发送的消息本身（字节数组）
            channel.basicPublish("","simple_queue2",null,msg.getBytes());
        }


        //关闭资源
        channel.close();
        connection.close();
    }
}
