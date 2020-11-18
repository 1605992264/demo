//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.ProcessActorDO;
//import com.txdata.flow.service.ProcessActorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// * 流程参与人表
// *
// * @author xiewh
// * @email xiewh@3xdata.cn
// * @date 2020-06-29 17:03:16
// */
//@RestController
//@RequestMapping("/catalog/processActor")
//public class ProcessActorController extends BaseController {
//	@Autowired
//	private ProcessActorService processActorService;
//
//	@PostMapping("/list")
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<ProcessActorDO> page = new Page<ProcessActorDO>(query.getPageNo(), query.getPageSize());
//		page = processActorService.page(page, query);
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
//    public R form(@RequestParam(required = true)String id){
//        ProcessActorDO processActor = processActorService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", processActor);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	public R save(ProcessActorDO processActor){
//		if (processActorService.save(processActor) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	public R remove(String id){
//		if (processActorService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		processActorService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	public R delete(String id){
//		if (processActorService.delete(id) > 0){//物理删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchDelete")
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		processActorService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	public R copy(String id){
//		if (processActorService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
