package hello.order;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
public class OrderServiceV0 implements OrderService{

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
}
