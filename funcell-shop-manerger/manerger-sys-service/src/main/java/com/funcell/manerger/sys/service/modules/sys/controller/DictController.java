package com.funcell.manerger.sys.service.modules.sys.controller;

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
import com.funcell.manerger.sys.service.modules.sys.entity.Dict;
import com.funcell.manerger.sys.service.modules.sys.entity.DictGroup;
import com.funcell.manerger.sys.service.modules.sys.service.IDictGroupService;
import com.funcell.manerger.sys.service.modules.sys.service.IDictService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description: 消息模版控制器
 */

@RestController
@RequestMapping("${funcell.admin.url.prefix}/sys/dict")
@ViewPrefix("modules/sys/dict")
@RequiresPathPermission("sys:dict")
@Log(title = "字典管理")
public class DictController extends BaseBeanController<Dict> {

	@Autowired
	private IDictService dictService;
	@Autowired
	private IDictGroupService dictGroupService;

	@GetMapping
	public ModelAndView list(Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		DictGroup group = dictGroupService.selectById(gid);
		model.addAttribute("group", group);
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
		EntityWrapper<Dict> entityWrapper = new EntityWrapper<>(entityClass);
		propertyPreFilterable.addQueryProperty("id");
		String gid = request.getParameter("gid");
		if (!StringUtils.isEmpty(gid)) {
			entityWrapper.eq("gid", gid);
		}else{
			entityWrapper.eq("gid", "undefined");
		}
		// 预处理
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
		PageResponse<Dict> pagejson = new PageResponse<>(dictService.list(queryable,entityWrapper));
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response,content);
	}

	@GetMapping(value = "add")
	public ModelAndView add(Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		model.addAttribute("gid", gid);
		model.addAttribute("data", new Dict());
		return displayModelAndView ("edit");
	}

	@PostMapping("add")
	@Log(logType = LogType.INSERT)
	@RequiresMethodPermissions("add")
	public Response add(Dict entity, BindingResult result,
						HttpServletRequest request, HttpServletResponse response) {
		// 验证错误
		this.checkError(entity,result);
		dictService.insert(entity);
		return Response.ok("添加成功");
	}

	@GetMapping(value = "{id}/update")
	public ModelAndView update(@PathVariable("id") String id, Model model, HttpServletRequest request,
							   HttpServletResponse response) {
		Dict entity = dictService.selectById(id);
		model.addAttribute("data", entity);
		return displayModelAndView ("edit");
	}

	@PostMapping("{id}/update")
	@Log(logType = LogType.UPDATE)
	@RequiresMethodPermissions("add")
	public Response update(Dict entity, BindingResult result,
						   HttpServletRequest request, HttpServletResponse response) {
		// 验证错误
		this.checkError(entity,result);
		dictService.insertOrUpdate(entity);
		return Response.ok("更新成功");
	}

	@PostMapping("{id}/delete")
	@Log(logType = LogType.DELETE)
	public Response delete(@PathVariable("id") String id) {
		dictService.deleteById(id);
		return Response.ok("删除成功");
	}

	@PostMapping("batch/delete")
	@Log(logType = LogType.DELETE)
	public Response batchDelete(@RequestParam("ids") String[] ids) {
		List<String> idList = java.util.Arrays.asList(ids);
		dictService.deleteBatchIds(idList);
		return Response.ok("删除成功");
	}

}