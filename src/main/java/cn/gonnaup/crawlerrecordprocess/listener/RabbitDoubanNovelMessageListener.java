package cn.gonnaup.crawlerrecordprocess.listener;

import cn.gonnaup.crawlerrecordprocess.config.RabbitConst;
import cn.gonnaup.crawlerrecordprocess.entity.DoubanNovel;
import cn.gonnaup.crawlerrecordprocess.repository.DoubanNovelRepository;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author gonnaup
 * @version created at 2022/4/21 15:39
 */
@Component
@RabbitListener(queues = RabbitConst.CRAWLER_DOUBAN_NOVEL_QUEUE)
public class RabbitDoubanNovelMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitDoubanNovelMessageListener.class);

    private final DoubanNovelRepository doubanNovelRepository;

    @Autowired
    public RabbitDoubanNovelMessageListener(DoubanNovelRepository doubanNovelRepository) {
        this.doubanNovelRepository = doubanNovelRepository;
    }

    @RabbitHandler
    void onMessage(DoubanNovel novel, MessageProperties properties, Channel channel) {
        doubanNovelRepository.save(novel);
        logger.info("收到小说 => {}", novel);
        long deliveryTag = properties.getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            logger.error("rabbitmq消息 [deliveryTage={}] 确认失败，原因: {}", deliveryTag, e.getMessage());
        }
    }

}
