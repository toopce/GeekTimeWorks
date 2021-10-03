import nettyserver.NettyHttpServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weimingjie
 * @version 1.0
 * @date 2021/10/3 10:01 下午
 */
public class RunNettyGateWay {
    public static void main(String[] args) throws InterruptedException {
        List<String> urls = new ArrayList<>();
        urls.add("http://localhost:8802");
        urls.add("http://localhost:8803");

        NettyHttpServer nettyHttpServer = new NettyHttpServer(8888,urls);
        nettyHttpServer.runNetty();
    }
}
