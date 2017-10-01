package handlers;

import dbservices.DBService;
import dbservices.MongoService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
//import io.netty.handler.codec.http.HttpServerHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import dao.MongoDAO;
import util.Config;

public class ServerInitializer extends ChannelInitializer<Channel> {

    private Config config;
    private DBService dbService;

    @Override
    public void initChannel(Channel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(65536));
        p.addLast(new ChunkedWriteHandler());
        p.addLast(new ServerHandler(dbService, config));
    }

    ServerInitializer(Config config, DBService dbService) {
        this.config = config;
        this.dbService = dbService;
    }

}
