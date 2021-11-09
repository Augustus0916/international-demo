# international-demo

set logs storage path in logback-spring.xml
set filebeat collect logs path in filebeat.yml
two path should be same

spring + hibernate + consul + gateway + swagger + prometheus + elasticsearch + filebeat + kibana + kafka + openFeign

avoid idea maven test

consul develop mod: localhost:8500localhost:8500     .\consul.exe agent -dev

prometheus: localhost:9090     .\prometheus.exe

gateway+swagger: localhost:8000/swagger-ui.html     router: ex: localhost:8000/demo-producer/ljh/augustus/test

elasticsearch: localhost:8001     start: .\elasticsearch.bat

kibana: localhost:8002     start: .\kibana.bat

filebeat: (1): .\install-service-filebeat.ps1     
          (2): .\filebeat.exe setup     
          (3): Start-Service filebeat
          (4): .\filebeat.exe modules enable elasticsearch     
          (5): .\filebeat.exe -e -c filebeat.yml

zookeeper: localhost:2181     .\zookeeper-server-start .\config\zookeeper.properties

kafka: .\kafka-server-start .\config\server.properties

front-end (vo.req) --> controller (dto) --> service (bo.po) --> dao
front-end (vo.res) <-- controller (dto) <-- service (bo.po) <-- dao

intercept all request exclude from gateway!!!
add filter in gateway which will add a header {"session":"UUID"} 
add an interceptor for global to stop all requests which do not contain header {"session":"UUID"}
when filter finish adding header, filter will use kafka to send the UUID to feign and interceptor
in the feign and interceptor, there is a kafka consumer to listen the message 
feign will add the header which comes from kafka consumer, and then the interceptor will not intercept the request from feign.





