package com.funcell.promotion.moudle.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.funcell.promotion.moudle.entity.CateGoryEntity;
import com.funcell.promotion.moudle.entity.CateGorySubEntity;

import java.util.List;

public interface ICateGorySubService {
    Page<CateGorySubEntity> getCateGorySubList(Page<CateGorySubEntity> page, int parentOptId);//查询配置的分类列表

    boolean insertCateGorySub(JSONObject json);

    List<CateGorySubEntity> getCateGoryEntitySubList(int optId);

    CateGorySubEntity getCateGoryEntity(int parentOptId,int optId);
}
