//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.OrderTaskDO;
//import com.txdata.flow.service.OrderTaskService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// * 流程实例执行表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:07
// */
//@RestController
//@RequestMapping("/orderTask")
//public class OrderTaskController extends BaseController {
//	@Autowired
//	private OrderTaskService orderTaskService;
//
//	@PostMapping("/list")
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<OrderTaskDO> page = new Page<OrderTaskDO>(query.getPageNo(), query.getPageSize());
//		page = orderTaskService.page(page, query);
//		// 封装分页数据
//		JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", page.getRecords());
//        jsonMap.put("pageSize", page.getSize());
//        jsonMap.put("pageNo", page.getCurrent());
//        jsonMap.put("count", page.getTotal());
//        return R.success(jsonMap);
//	}
//
//    @PostMapping("/form")
//	public R form(@RequestParam(required = true)String id){
//        OrderTaskDO orderTask = orderTaskService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", orderTask);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	public R save(@Validated OrderTaskDO orderTask){
//		if (orderTaskService.save(orderTask) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	public R remove(String id){
//		if (orderTaskService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		orderTaskService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	public R delete(String id){
//		if (orderTaskService.delete(id) > 0){//物理删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchDelete")
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		orderTaskService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	public R copy(String id){
//		if (orderTaskService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
