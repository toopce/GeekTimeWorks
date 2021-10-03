package router;

import java.util.List;
import java.util.Random;

/**
 * @author weimingjie
 * @version 1.0
 * @date 2021/9/27 11:05 上午
 */
public class RandomHttpEndPointRouter implements HttpEndPointRouter{
    @Override
    public String route(List<String> endpoints) {
        //随机数去调用服务，实现负载均衡
        int size = endpoints.size();
        Random random = new Random(System.currentTimeMillis());
        return endpoints.get(random.nextInt(size));
    }

}
