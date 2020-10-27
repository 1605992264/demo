//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.HiCirculationDetailedDO;
//import com.txdata.flow.service.HiCirculationDetailedService;
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
// * 流程流转历史操作人表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-08-04 09:57:03
// */
//@RestController
//@RequestMapping("/hiCirculationDetailed")
//public class HiCirculationDetailedController extends BaseController {
//	@Autowired
//	private HiCirculationDetailedService hiCirculationDetailedService;
//
//	@PostMapping("/list")
//	//@Log(value = "#{@user}用户查询了[流程流转历史操作人表]列表，查询参数为:", paramaType = ParamType.MAP, operationType = OperationType.VIEW)
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<HiCirculationDetailedDO> page = new Page<HiCirculationDetailedDO>(query.getPageNo(), query.getPageSize());
//		page = hiCirculationDetailedService.page(page, query);
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
//	//@Log(value = "#{@user}用户查询了[流程流转历史操作人表]数据，查询参数为:", operationType = OperationType.VIEW, paramaType = ParamType.FORM)
//    public R form(@RequestParam(required = true)String id){
//        HiCirculationDetailedDO hiCirculationDetailed = hiCirculationDetailedService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", hiCirculationDetailed);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	//@Log(value = "#{@user}用户#{hiCirculationDetailed.operationType}了[流程流转历史操作人表]数据，#{hiCirculationDetailed.operationType}参数为:",
//	//	operationType = OperationType.SAVE, paramaType = ParamType.BEAN)
//	public R save(@Validated HiCirculationDetailedDO hiCirculationDetailed){
//		if (hiCirculationDetailedService.save(hiCirculationDetailed) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	//@Log(value = "#{@user}用户删除了[流程流转历史操作人表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R remove(String id){
//		if (hiCirculationDetailedService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	//@Log(value = "#{@user}用户批量删除了[流程流转历史操作人表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		hiCirculationDetailedService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	//@Log(value = "#{@user}用户删除了[流程流转历史操作人表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R delete(String id){
//	    hiCirculationDetailedService.delete(id);//物理删除
//        return R.success();
//	}
//
//	@PostMapping("/batchDelete")
//	//@Log(value = "#{@user}用户删除了[流程流转历史操作人表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		hiCirculationDetailedService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	//@Log(value = "#{@user}用户复制了[流程流转历史操作人表]数据，使用参数为:", paramaType = ParamType.FORM)
//	public R copy(String id){
//		if (hiCirculationDetailedService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
