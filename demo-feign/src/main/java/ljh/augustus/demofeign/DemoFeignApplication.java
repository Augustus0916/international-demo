package ljh.augustus.demofeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(value = "ljh.augustus")
@ComponentScan(value = "ljh.augustus")
public class DemoFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFeignApplication.class, args);
	}

}
