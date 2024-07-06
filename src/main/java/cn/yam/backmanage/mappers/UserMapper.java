package cn.yam.backmanage.mappers;

import cn.yam.backmanage.entity.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
//    User selectByUsername(String username);
//
//    void insert(User user);
//
//    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%', #{search}, '%') LIMIT #{limit} OFFSET #{offset}")
//    List<User> findUsers(@Param("search") String search, @Param("offset") int offset, @Param("limit") int limit);
//
//    @Select("SELECT COUNT(*) FROM user WHERE username LIKE CONCAT('%', #{search}, '%')")
//    int countUsers(@Param("search") String search);
//
//    void deleteUser(int userId);
//
//    User selectByUserId(String s);
//
//    void update(User userTemp);
}
