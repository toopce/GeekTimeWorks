package filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author weimingjie
 * @version 1.0
 * @date 2021/9/27 11:00 上午
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("hi","wmj");
    }
}
