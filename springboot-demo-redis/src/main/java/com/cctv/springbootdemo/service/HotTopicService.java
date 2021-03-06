package com.cctv.springbootdemo.service;

/**
 * @ClassName: HotTopicService
 * @Description: 测试缓存击穿Service
 * @Author: Eric.Zhang
 * @Date: 2020/5/9 15:43
 * @version: 1.0
 */
public interface HotTopicService {
    /**
     * 获取热点话题
     *
     * @return 热点话题str
     */
    String getHotTopic();
}
