package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {
    void order();
    void cancel();
    AtomicInteger getStock(); //멀티스레드 환경에서 안전하게 값을 증가시키는 Integer

}
