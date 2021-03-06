package cn.itcast.consumer.pojo;

import lombok.Data;
import java.util.Date;

/**
 * @author ryz2593
 * @date 2020/1/14 17:21
 */
@Data
public class User {
    private Long id;

    private String userName;

    private String password;

    private String name;

    private Integer age;

    private Integer sex;

    private String note;

    private Date birthday;

    private Date createTime;

    private Date updateTime;

    /**
     * //如果字段不需要持久化到数据库使用Transient注解修饰
     * //表示该字段是瞬时的
     */
    private String extra;
}

