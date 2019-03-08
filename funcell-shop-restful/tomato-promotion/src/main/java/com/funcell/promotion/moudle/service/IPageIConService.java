package com.funcell.promotion.moudle.service;

import com.alibaba.fastjson.JSONObject;
import com.funcell.promotion.moudle.entity.PageIConEntity;

public interface IPageIConService {

    PageIConEntity getPageIConEntity(String markId);

    boolean insertPageICon(JSONObject json);

    boolean updatePageIcon(JSONObject json);

}
