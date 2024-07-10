package cn.yam.backmanage.mappers;

import cn.yam.backmanage.entity.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
