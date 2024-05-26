package cn.yam.backmanage.service;

import cn.yam.backmanage.entity.algo.LuoGuData;
import org.springframework.stereotype.Service;

public interface LuoGuService {

    public LuoGuData getLuoGuInfo(String username) throws Exception;
}
