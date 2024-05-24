package cn.yam.backmanage.entity.Response;

import cn.yam.backmanage.entity.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能：
 * 日期：2024/5/24 下午6:02
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserResponse {
    private List<User> users;
    private int total;
}