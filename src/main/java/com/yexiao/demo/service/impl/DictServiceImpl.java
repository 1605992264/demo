package com.yexiao.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.domain.DictDO;
import com.yexiao.demo.mapper.DictMapper;
import com.yexiao.demo.service.DictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author xuhf
* @date 2020-08-28 16:38:36.313
**/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictServiceImpl extends ServiceImpl<DictMapper,DictDO> implements DictService {


}
