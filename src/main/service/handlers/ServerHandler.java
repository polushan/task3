package handlers;

import dbservices.DBService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import java.io.IOException;

import static io.netty.handler.codec.http.HttpMethod.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.*;

import util.Config;


public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private DBService dbService;
    private Config config;

    ServerHandler(DBService dbService, Config config) {
        this.dbService = dbService;
        this.config = config;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws IOException {
        if (request.method() != GET) {
            sendError(ctx, METHOD_NOT_ALLOWED);
            return;
        }
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
        if (!queryStringDecoder.path().equalsIgnoreCase(config.ADDRESS)) {
            return;
        }
        String imageName = queryStringDecoder.parameters().entrySet().stream()
                .filter(p -> "name".equals(p.getKey()))
                .map(p -> p.getValue().get(0)).findAny().orElse("");
        byte[] b = dbService.getImage(imageName);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "image/jpeg");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, b.length);
        response.content().writeBytes(b);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
