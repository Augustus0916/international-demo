package ljh.augustus.demogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "ljh.augustus")
public class DemoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGatewayApplication.class, args);
	}

}
