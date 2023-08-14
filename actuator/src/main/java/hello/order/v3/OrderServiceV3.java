package hello.order.v3;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV3 implements OrderService {

    private final MeterRegistry registry;

    private AtomicInteger stock = new AtomicInteger(100);//재고 초기값 100으로 설정

    public OrderServiceV3(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(registry);

        timer.record(() -> {
            log.info("주문");
            stock.decrementAndGet(); //재고값 줄어듬
            sleep(500); //0.5초
        });
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i + new Random().nextInt(200)); //랜덤 타임 넣어보기
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancel() {
        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(registry);

        timer.record(() -> {
            log.info("취소");
            stock.incrementAndGet(); //재고값 늘어남
            sleep(200); //0.2초
        });
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
