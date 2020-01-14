package cn.itcast.user.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author ryz2593
 * @date 2020/1/13 15:47
 * <p>
 * 需要设置表名，以及主键
 */
@Data
@Table(name = "tb_user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
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
    @Transient
    private String extra;
}
