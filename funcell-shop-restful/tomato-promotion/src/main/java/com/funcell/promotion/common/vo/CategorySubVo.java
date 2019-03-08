package com.funcell.promotion.common.vo;

import com.funcell.promotion.moudle.entity.CateGorySubEntity;
import lombok.Data;

import java.util.List;

@Data
public class CategorySubVo extends BaseTomatoVo {

    List<CateGorySubEntity> list;
    long total;
}
