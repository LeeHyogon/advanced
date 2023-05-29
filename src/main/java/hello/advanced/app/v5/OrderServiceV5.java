package hello.advanced.app.v5;


import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;

    public OrderServiceV5(OrderRepositoryV5 orderRepository,LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template =  new TraceTemplate(trace);
    }

    private final TraceTemplate template;

    public void orderItem( String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
