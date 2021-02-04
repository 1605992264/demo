package com.yexiao.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.domain.LogDO;
import com.yexiao.demo.mapper.LogMapper;
import com.yexiao.demo.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author xuhf
* @date 2020-10-23 15:14:47.743
**/
@Service
@Transactional(rollbackFor = Exception.class)
public class LogServiceImpl extends ServiceImpl<LogMapper, LogDO> implements LogService {


}
