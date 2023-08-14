package hello.order.v0;

import hello.order.OrderService;
import hello.order.v3.OrderServiceV3;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
public class OrderServiceV0 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);//재고 초기값 100으로 설정

    @Override
    public void order() {
      log.info("주문");
      stock.decrementAndGet(); //재고값 줄어듬
    }

    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet(); //재고값 늘어남

    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }

    @Configuration
    public static class OrderConfigV3 {

        @Bean
        OrderService orderService(MeterRegistry registry) {
            return new OrderServiceV3(registry);
        }
    }
}
