package ljh.augustus.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "house_info")
@Entity
@Setter
@Getter
public class TestBeanInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "city")
    private String city; //城市
    @Column(name = "community")
    private String community; //小区
    @Column(name = "cost")
    private String cost; //均价
    @Column(name = "type")
    private String type; //类型
    @Column(name = "block")
    private String block; //区
    @Column(name = "zone")
    private String zone; //街道
    @Column(name = "year")
    private String year;
}
