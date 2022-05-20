package cn.gonnaup.crawlerrecordprocess.config;

import cn.gonnaup.crawlerrecordprocess.entity.DoubanNovel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gonnaup
 * @version created at 2022/4/21 15:11
 */
@Configuration
public class RabbitConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    private final AmqpAdmin rabbitAdmin;

    @Autowired
    public RabbitConfig(AmqpAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        /**
         * 设置id-class映射器，需要生产端指定headers属性"__TypeId__"为此映射器id
         * @see org.springframework.amqp.support.converter.AbstractJavaTypeMapper#DEFAULT_CLASSID_FIELD_NAME
         */
        Map<String, Class<?>> idClassMapping = new HashMap<>(1);
        idClassMapping.put("novel", DoubanNovel.class);
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setIdClassMapping(idClassMapping);
        jackson2JsonMessageConverter.setJavaTypeMapper(typeMapper);
        return jackson2JsonMessageConverter;
    }

    @PostConstruct
    void declareBind() {
        TopicExchange exchange = new TopicExchange(RabbitConst.CRAWLER_EXCHANGE);
        rabbitAdmin.declareExchange(exchange);
        Queue queue = new Queue(RabbitConst.CRAWLER_DOUBAN_NOVEL_QUEUE);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(RabbitConst.CRAWLER_DOUBAN_NOVEL_QUEUE_ROUTINGKEY));
        logger.info("声明exchange=>{}, queue=>{}, 绑定key => {}", exchange.getName(), queue.getName(), RabbitConst.CRAWLER_DOUBAN_NOVEL_QUEUE_ROUTINGKEY);
    }
}
