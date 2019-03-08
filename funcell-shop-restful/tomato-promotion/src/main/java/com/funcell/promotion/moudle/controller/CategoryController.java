package com.funcell.promotion.moudle.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.funcell.manerger.sys.common.base.http.Response;
import com.funcell.promotion.common.emum.ErrorCodeEnum;
import com.funcell.promotion.common.vo.CategoryVo;
import com.funcell.promotion.moudle.entity.CateGoryEntity;
import com.funcell.promotion.moudle.service.ICateGoryService;
import com.funcell.promotion.utils.MessageUtils;
import com.funcell.promotion.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 商品分类控制器
 */
@RestController
public class CategoryController {

    @Autowired
    private ICateGoryService cateGoryService;//商品分类服务

    /**
     * 查询配置的主页类别
     *
     * @return
     */
    @GetMapping(value = "/category")
    public Response getCateGory(HttpServletRequest request) {

        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");

        Page pageEntity = new Page<CateGoryEntity>(PageUtils.getPage(page), PageUtils.getPageSize(pageSize));

        Page<CateGoryEntity> pageList = cateGoryService.getCateGoryList(pageEntity);

        List<CateGoryEntity> list = pageList.getRecords();
        if (null == list) {
            return Response.error(ErrorCodeEnum.COUPON2012.code(), ErrorCodeEnum.COUPON2012.msg());
        }

        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setList(list);
        categoryVo.setTotal(pageList.getTotal());
        return Response.ok().putObject(categoryVo);

    }

    /**
     * 配置类别接口
     *
     * @return
     */
    @PostMapping(value = "/category")
    public Response putCateGory(@RequestBody JSONObject json) {

        return cateGoryService.insertCateGory(json) ? Response.ok() : Response.error(ErrorCodeEnum.COUPON2013.code(), ErrorCodeEnum.COUPON2013.msg());

    }


}
