package filter;

import common.StatusEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author weimingjie
 * @version 1.0
 * @date 2021/9/27 11:02 上午
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("hello","wmj");
    }

    //    @Override
//    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
//        //获取sid判断是否存在与redis中
//        String sid = fullRequest.headers().get("sid");
//        if (true){
//            //延长redis的过期时间
//            //并且在头中写入一个yes
//            fullRequest.headers().set("status",StatusEnum.PASS);
//        }else {
//            //判断并不在redis中则进行数据判断此账号是否在数据库中存在
//            if (true){
//                //存在就将sid写入redis中并赋予过期日期
//                fullRequest.headers().set("status", StatusEnum.PASS);
//            }else{
//                //不存在则在头重写入一个flase
//                fullRequest.headers().set("status",StatusEnum.REJECTED);
//            }
//        }
//
//    }
}
