package ljh.augustus.demo.dto;

import lombok.Data;

@Data
public class TestBeanDto {

    private Integer id;
    private String city; //城市
    private String community; //小区
    private String cost; //均价
    private String type; //类型
    private String block; //区
    private String zone; //街道
}
