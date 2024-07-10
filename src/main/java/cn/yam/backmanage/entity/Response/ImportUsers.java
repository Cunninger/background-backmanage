package cn.yam.backmanage.entity.Response;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import cn.yam.backmanage.entity.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能：
 * 日期：2024/7/10 下午4:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImportUsers {

    private List<User> users;
}