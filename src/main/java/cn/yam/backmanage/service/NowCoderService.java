package cn.yam.backmanage.service;

import cn.yam.backmanage.entity.algo.NowCoderData;

public interface NowCoderService {

    public NowCoderData getNowCoderInfo(String name) throws Exception;
}
