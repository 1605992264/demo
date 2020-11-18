//package com.yexiao.demo.flow.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.SystemDO;
//import com.txdata.flow.service.SystemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 第三方系统表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:37
// */
//@Controller
//@RequestMapping("/flow/system")
//public class SystemController extends BaseController {
//	@Autowired
//	private SystemService systemService;
//
//	@ResponseBody
//	@PostMapping("/list")
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<SystemDO> page = new Page<SystemDO>(query.getPageNo(), query.getPageSize());
//		page = systemService.page(page, query);
//		// 封装分页数据
//		Map<String,Object> jsonMap = new HashMap<String,Object>();
//        jsonMap.put("rows", page.getRecords());
//        jsonMap.put("pageSize", page.getSize());
//        jsonMap.put("pageNo", page.getCurrent());
//        jsonMap.put("count", page.getTotal());
//        return R.success(jsonMap);
//	}
//
//    @ResponseBody
//    @PostMapping("/form")
//    public R form(@RequestParam(required = true)String id){
//        SystemDO system = systemService.get(id);
//        Map<String,Object> jsonMap = new HashMap<String,Object>();
//        jsonMap.put("system", system);
//        return R.success(jsonMap);
//    }
//
//	@ResponseBody
//	@PostMapping("/save")
//	public R save(SystemDO system){
//		if(systemService.save(system)>0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	@ResponseBody
//	public R remove(String id){
//		if(systemService.remove(id)>0){
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	@ResponseBody
//	public R remove(@RequestParam("ids") String[] ids){
//		systemService.batchRemove(ids);
//		return R.success();
//	}
//
//}
