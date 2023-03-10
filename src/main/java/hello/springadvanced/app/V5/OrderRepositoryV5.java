package hello.springadvanced.app.V5;

import hello.springadvanced.trace.callback.TraceCallback;
import hello.springadvanced.trace.callback.TraceTemplate;
import hello.springadvanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId){
        template.execute("OrderRepository.save()", new TraceCallback<>() {
            @Override
            public Void call() {
                if (itemId.equals("ex")){
                    throw new IllegalStateException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        });
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
