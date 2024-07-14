package cn.yam.backmanage.entity.pojo;

import cn.yam.backmanage.object.AbstractDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)//  重写equals和hashcode方法
@TableName("sys_role")
public class SysRole extends AbstractDO {
    private String name;

    private String description;

    private Boolean available;

}