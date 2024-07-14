package cn.yam.backmanage.mappers;

import cn.yam.backmanage.entity.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 涉及多表查询
/*    @Select("SELECT u.*, r.id AS role_id, r.name AS role_name, r.description AS role_description, r.available AS role_available " +
            "FROM user u " +
            "LEFT JOIN sys_role r ON u.sys_role_id = r.id " +
            "WHERE u.username = #{username}")
    @Results({
            @Result(column = "role_id", property = "sysRole.id"),
            @Result(column = "role_name", property = "sysRole.name"),
            @Result(column = "role_description", property = "sysRole.description"),
            @Result(column = "role_available", property = "sysRole.available")
    })*/
    User findUserWithRoleByUsername(@Param("username") String username);

    IPage<User> selectPage(Page<User> userPage, String search);
};
