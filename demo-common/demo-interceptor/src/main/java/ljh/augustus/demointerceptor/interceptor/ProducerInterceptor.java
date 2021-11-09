package ljh.augustus.demointerceptor.interceptor;

import ljh.augustus.demokafka.consumer.InterceptorConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.Duration;

@Slf4j
@Component
public class ProducerInterceptor implements HandlerInterceptor {

    @Autowired
    private InterceptorConsumer interceptorConsumer;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        String session = "gateway";

        Consumer<String, String> consumer = interceptorConsumer.getInterceptorConsumer();
        Duration durationSeconds = Duration.ofSeconds(3);
        ConsumerRecords<String, String> records = consumer.poll(durationSeconds);
        for (ConsumerRecord<String, String> record : records) {
            log.info("record.value() in interceptor: " + record.value());
            session = record.value();
        }

        String reqId = request.getHeader("session");
        if(!reqId.equals(session)) {
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("Error, you should visit through gateway!!!");
            return false;
        }
        return true;
    }

//    /**
//     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
//    }
//
//    /**
//     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
//    }
}
