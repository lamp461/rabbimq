package net.oiyou.rabbitmq.five;

import com.rabbitmq.client.Channel;
import net.oiyou.rabbitmq.utils.RabbitMqUtil;

import java.util.Scanner;

/**
 * @author HuangBoo
 * @since 2023年05月01日 13:12
 */
public class EmitLog {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtil.getChannel();
        //交换机名称，扇出类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String next = scanner.next();
            channel.basicPublish(EXCHANGE_NAME, "", null, next.getBytes());
            System.out.println("生产者发出：" + next);
        }

    }
}
