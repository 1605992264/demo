//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.FormReloDO;
//import com.txdata.flow.service.FormReloService;
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
// * 流程节点表单权限表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:07:26
// */
//@RestController
//@RequestMapping("/formRelo")
//public class FormReloController extends BaseController {
//	@Autowired
//	private FormReloService formReloService;
//
//	@PostMapping("/list")
//	//@Log(value = "#{@user}用户查询了[流程节点表单权限表]列表，查询参数为:", paramaType = ParamType.MAP, operationType = OperationType.VIEW)
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<FormReloDO> page = new Page<FormReloDO>(query.getPageNo(), query.getPageSize());
//		page = formReloService.page(page, query);
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
//	//@Log(value = "#{@user}用户查询了[流程节点表单权限表]数据，查询参数为:", operationType = OperationType.VIEW, paramaType = ParamType.FORM)
//    public R form(@RequestParam(required = true)String id){
//        FormReloDO formRelo = formReloService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", formRelo);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	//@Log(value = "#{@user}用户#{formRelo.operationType}了[流程节点表单权限表]数据，#{formRelo.operationType}参数为:",
//	//	operationType = OperationType.SAVE, paramaType = ParamType.BEAN)
//	public R save(@Validated FormReloDO formRelo){
//		if (formReloService.save(formRelo) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	//@Log(value = "#{@user}用户删除了[流程节点表单权限表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R remove(String id){
//		if (formReloService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	//@Log(value = "#{@user}用户批量删除了[流程节点表单权限表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		formReloService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	//@Log(value = "#{@user}用户删除了[流程节点表单权限表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R delete(String id){
//		formReloService.delete(id);//物理删除
//		    return R.success();
//	}
//
//	@PostMapping("/batchDelete")
//	//@Log(value = "#{@user}用户删除了[流程节点表单权限表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		formReloService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	//@Log(value = "#{@user}用户复制了[流程节点表单权限表]数据，使用参数为:", paramaType = ParamType.FORM)
//	public R copy(String id){
//		if (formReloService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
