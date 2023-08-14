package hello.order.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV1 implements OrderService {

    private final MeterRegistry registry;

    private AtomicInteger stock = new AtomicInteger(100);//재고 초기값 100으로 설정

    public OrderServiceV1(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
      log.info("주문");
      stock.decrementAndGet(); //재고값 줄어듬

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(registry).increment();
    }

    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet(); //재고값 늘어남

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method","cancel")
                .description("cancel")
                .register(registry).increment();

    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
