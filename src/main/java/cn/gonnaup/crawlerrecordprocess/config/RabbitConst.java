package cn.gonnaup.crawlerrecordprocess.config;

/**
 * rabbitmq 常数
 * @author gonnaup
 * @version created at 2022/4/21 15:12
 */
public abstract class RabbitConst {

    public static final String CRAWLER_EXCHANGE = "_exchange_crawler";

    public static final String CRAWLER_DOUBAN_NOVEL_QUEUE = "_crawler_douban_novel";

    public static final String CRAWLER_DOUBAN_NOVEL_QUEUE_ROUTINGKEY = "crawler.douban.novel";
}
