package cn.yam.backmanage.entity.pojo;

import cn.yam.backmanage.object.AbstractDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 日期：2024/5/24 下午6:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User extends AbstractDO {

    @TableField(exist = false)
    private SysRole sysRole;
    private String username;

    private String password;

    @TableField("last_login_time")
    private Date lastLoginTime;

    private Boolean status;

}