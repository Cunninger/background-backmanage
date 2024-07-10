package cn.yam.backmanage.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：
 * 日期：2024/5/24 上午8:30
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("user") // 指定表名，如果表名与类名一致可以省略
public class User {
    @TableId // 指定主键
    private Integer userId = null;
    private String username;
    private String password;
    private String role;


}


