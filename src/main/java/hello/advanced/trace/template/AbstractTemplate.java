package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace=trace;
    }
    public T execute(String message){
        TraceStatus status = null;
        try{
            status=trace.begin("OrderController.request()");
            //로직 호출
            T result=call();
            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status,e);
            throw e; //예외를 꼭 다시 던저주어야 한다.
        }
    }
    protected abstract T call();
}
