package router;

import java.util.List;

public interface HttpEndPointRouter {
    String route(List<String> endpoints);
}
