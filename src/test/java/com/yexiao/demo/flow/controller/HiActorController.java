//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.HiActorDO;
//import com.txdata.flow.service.HiActorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
////import com.txdata.common.log.annotation.Log;
////import com.txdata.common.log.annotation.OperationType;
////import com.txdata.common.log.annotation.ParamType;
//
///**
// * 流程历史审批记录表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:01
// */
//@RestController
//@RequestMapping("/hiActor")
//public class HiActorController extends BaseController {
//	@Autowired
//	private HiActorService hiActorService;
//
//	@PostMapping("/list")
//	//@Log(value = "#{@user}用户查询了[流程历史审批记录表]列表，查询参数为:", paramaType = ParamType.MAP, operationType = OperationType.VIEW)
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<HiActorDO> page = new Page<HiActorDO>(query.getPageNo(), query.getPageSize());
//		page = hiActorService.page(page, query);
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
//	//@Log(value = "#{@user}用户查询了[流程历史审批记录表]数据，查询参数为:", operationType = OperationType.VIEW, paramaType = ParamType.FORM)
//    public R form(@RequestParam(required = true)String id){
//        HiActorDO hiActor = hiActorService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", hiActor);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	//@Log(value = "#{@user}用户#{hiActor.operationType}了[流程历史审批记录表]数据，#{hiActor.operationType}参数为:",
//	//	operationType = OperationType.SAVE, paramaType = ParamType.BEAN)
//	public R save(@Validated HiActorDO hiActor){
//		if (hiActorService.save(hiActor) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	//@Log(value = "#{@user}用户删除了[流程历史审批记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R remove(String id){
//		if (hiActorService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	//@Log(value = "#{@user}用户批量删除了[流程历史审批记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		hiActorService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	//@Log(value = "#{@user}用户删除了[流程历史审批记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R delete(String id){
//		hiActorService.delete(id);//物理删除
//		    return R.success();
//	}
//
//	@PostMapping("/batchDelete")
//	//@Log(value = "#{@user}用户删除了[流程历史审批记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		hiActorService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	//@Log(value = "#{@user}用户复制了[流程历史审批记录表]数据，使用参数为:", paramaType = ParamType.FORM)
//	public R copy(String id){
//		if (hiActorService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
