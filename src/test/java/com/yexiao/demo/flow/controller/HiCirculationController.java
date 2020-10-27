//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.HiCirculationDO;
//import com.txdata.flow.service.HiCirculationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
////import com.txdata.common.log.annotation.Log;
////import com.txdata.common.log.annotation.OperationType;
////import com.txdata.common.log.annotation.ParamType;
//
///**
// * 流程实例历史记录表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-08-04 09:54:44
// */
//@RestController
//@RequestMapping("/hiCirculation")
//public class HiCirculationController extends BaseController {
//	@Autowired
//	private HiCirculationService hiCirculationService;
//
//	@PostMapping("/list")
//	//@Log(value = "#{@user}用户查询了[流程实例历史记录表]列表，查询参数为:", paramaType = ParamType.MAP, operationType = OperationType.VIEW)
//	public R list(@RequestParam Map<String, Object> params){
//		List<HiCirculationDO> hiCirculationDOS = hiCirculationService.list(params);
//		// 封装分页数据
//		JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", hiCirculationDOS);
//        return R.success(jsonMap);
//	}
//
//    @PostMapping("/form")
//	//@Log(value = "#{@user}用户查询了[流程实例历史记录表]数据，查询参数为:", operationType = OperationType.VIEW, paramaType = ParamType.FORM)
//    public R form(@RequestParam(required = true)String id){
//        HiCirculationDO hiCirculation = hiCirculationService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", hiCirculation);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	//@Log(value = "#{@user}用户#{hiCirculation.operationType}了[流程实例历史记录表]数据，#{hiCirculation.operationType}参数为:",
//	//	operationType = OperationType.SAVE, paramaType = ParamType.BEAN)
//	public R save(@Validated HiCirculationDO hiCirculation){
//		if (hiCirculationService.save(hiCirculation) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	//@Log(value = "#{@user}用户删除了[流程实例历史记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R remove(String id){
//		if (hiCirculationService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	//@Log(value = "#{@user}用户批量删除了[流程实例历史记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		hiCirculationService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	//@Log(value = "#{@user}用户删除了[流程实例历史记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R delete(String id){
//		hiCirculationService.delete(id);//物理删除
//		    return R.success();
//	}
//
//	@PostMapping("/batchDelete")
//	//@Log(value = "#{@user}用户删除了[流程实例历史记录表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		hiCirculationService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	//@Log(value = "#{@user}用户复制了[流程实例历史记录表]数据，使用参数为:", paramaType = ParamType.FORM)
//	public R copy(String id){
//		if (hiCirculationService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
