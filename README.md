# international-demo

spring+hibernate+consul+gateway+swagger+prometheus+elasticsearch+filebeat+kibana

avoid idea maven test

consul进入开发者模式：consul agent -dev

consul: localhost:8500

prometheus: localhost:9090

gateway+swagger: localhost:8000/swagger-ui.html

elasticsearch: localhost:8001     start: .\elasticsearch.bat

kibana: localhost:8002     start: .\kibana.bat

filebeat: (1): .\install-service-filebeat.ps1     
          (2): .\filebeat.exe setup     
          (3): Start-Service filebeat
          (4): .\filebeat.exe modules enable elasticsearch     
          (5): .\filebeat.exe -e -c filebeat.yml





