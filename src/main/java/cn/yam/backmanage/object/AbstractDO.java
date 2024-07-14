package cn.yam.backmanage.object;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractDO implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Date createTime;
    private Date updateTime;

}