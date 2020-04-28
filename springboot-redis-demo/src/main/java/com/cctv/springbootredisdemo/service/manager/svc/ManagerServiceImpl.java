package com.cctv.springbootredisdemo.service.manager.svc;

import com.alibaba.fastjson.JSONObject;
import com.cctv.springbootredisdemo.config.redis.RedisUtils;
import com.cctv.springbootredisdemo.persistent.manager.pl.ManagerDAO;
import com.cctv.springbootredisdemo.persistent.manager.po.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Eric
 * @Date: 2020/1/9 20:42
 */
@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    private ManagerDAO managerDAO;
    private RedisUtils redisUtils;

    @Autowired(required = false)
    public ManagerServiceImpl(ManagerDAO managerDAO, RedisUtils redisUtils) {
        this.managerDAO = managerDAO;
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean loginCheck(String managerEmail, String inputPassword) {
        boolean result = false;
        Manager targetManager;
        //从缓存获取用户信息，若信息不存在，从表中获取，并存入缓存
        if (redisUtils.exists(managerEmail)) {
            targetManager = JSONObject.parseObject((String) redisUtils.get(managerEmail), Manager.class);
            if (targetManager.getAccountPassword().equals(inputPassword)) {
                result = true;
            }
            log.info("从缓存中获取了用户！");
        } else {
            targetManager = managerDAO.getManagerByEmail(managerEmail);
            if (targetManager == null) {
                log.info("未找到对应用户！");
                return false;
            }
            redisUtils.set(managerEmail, JSONObject.toJSONString(targetManager));
            result = true;
            log.info("将对应用户存入缓存！");
        }
        return result;
    }

    @Override
    public void insertManager(Manager manager) {
        managerDAO.insertManager(manager);
    }
}
