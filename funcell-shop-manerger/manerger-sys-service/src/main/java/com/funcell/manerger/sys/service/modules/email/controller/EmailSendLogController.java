package com.funcell.manerger.sys.service.modules.email.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.funcell.manerger.sys.common.base.http.PageResponse;
import com.funcell.manerger.sys.common.base.http.Response;
import com.funcell.manerger.sys.common.base.mvc.annotation.ViewPrefix;
import com.funcell.manerger.sys.common.base.mvc.controller.BaseBeanController;
import com.funcell.manerger.sys.common.mybatis.wrapper.EntityWrapper;
import com.funcell.manerger.sys.common.query.annotation.PageableDefaults;
import com.funcell.manerger.sys.common.query.data.PropertyPreFilterable;
import com.funcell.manerger.sys.common.query.data.Queryable;
import com.funcell.manerger.sys.common.query.utils.QueryableConvertUtils;
import com.funcell.manerger.sys.common.security.shiro.authz.annotation.RequiresMethodPermissions;
import com.funcell.manerger.sys.common.security.shiro.authz.annotation.RequiresPathPermission;
import com.funcell.manerger.sys.common.utils.StringUtils;
import com.funcell.manerger.sys.service.aspectj.annotation.Log;
import com.funcell.manerger.sys.service.aspectj.enums.LogType;
import com.funcell.manerger.sys.service.modules.email.entity.EmailSendLog;
import com.funcell.manerger.sys.service.modules.email.service.IEmailSendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @title: 邮件发送日志控制器
 * @description: 邮件发送日志控制器
 */
@RestController
@RequestMapping("${funcell.admin.url.prefix}/email/sendlog")
@RequiresPathPermission("email:sendlog")
@ViewPrefix("modules/email/sendlog")
@Log(title = "邮件发送日志")
public class EmailSendLogController extends BaseBeanController<EmailSendLog> {

    @Autowired
    private IEmailSendLogService emailSendLogService;


    @GetMapping
    @RequiresMethodPermissions("view")
    public ModelAndView list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return displayModelAndView("list");
    }

    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    @Log(logType = LogType.SELECT)
    @RequiresMethodPermissions("list")
    public void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        EntityWrapper<EmailSendLog> entityWrapper = new EntityWrapper<>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageResponse<EmailSendLog> pagejson = new PageResponse<>(emailSendLogService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response,content);
    }

    @PostMapping("{id}/delete")
    @Log(logType = LogType.DELETE)
    @RequiresMethodPermissions("delete")
    public Response delete(@PathVariable("id") String id) {
        emailSendLogService.deleteById(id);
        return Response.ok("删除成功");
    }

    @PostMapping("batch/delete")
    @Log(logType = LogType.DELETE)
    @RequiresMethodPermissions("delete")
    public Response batchDelete(@RequestParam("ids") String[] ids) {
        List<String> idList = java.util.Arrays.asList(ids);
        emailSendLogService.deleteBatchIds(idList);
        return Response.ok("删除成功");
    }

}