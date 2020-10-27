//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.HiOperateDO;
//import com.txdata.flow.service.HiOperateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
////import com.txdata.common.log.annotation.Log;
////import com.txdata.common.log.annotation.OperationType;
////import com.txdata.common.log.annotation.ParamType;
//
///**
// * 流转历史操作表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-07 11:09:15
// */
//@RestController
//@RequestMapping("/hiOperate")
//public class HiOperateController extends BaseController {
//	@Autowired
//	private HiOperateService hiOperateService;
//
//	/**
//	 * 专门针对启动流程对流转历史表进行操作
//	 * @param params
//	 * @return
//	 */
//	@PostMapping("/list")
//	@ResponseBody
//	public R list(@RequestParam Map<String, Object> params){
//		List<HiOperateDO>  hiOperateDOList = hiOperateService.queryPointProcessHistory(params);
//		// 封装分页数据
//		JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", hiOperateDOList);
//        return R.success(jsonMap);
//	}
//
//    @PostMapping("/form")
//	//@Log(value = "#{@user}用户查询了[流转历史操作表]数据，查询参数为:", operationType = OperationType.VIEW, paramaType = ParamType.FORM)
//    public R form(@RequestParam(required = true)String id){
//        HiOperateDO hiOperate = hiOperateService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", hiOperate);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	//@Log(value = "#{@user}用户#{hiOperate.operationType}了[流转历史操作表]数据，#{hiOperate.operationType}参数为:",
//	//	operationType = OperationType.SAVE, paramaType = ParamType.BEAN)
//	public R save(@Validated HiOperateDO hiOperate){
//		if (hiOperateService.save(hiOperate) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	//@Log(value = "#{@user}用户删除了[流转历史操作表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R remove(String id){
//		if (hiOperateService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	//@Log(value = "#{@user}用户批量删除了[流转历史操作表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		hiOperateService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	//@Log(value = "#{@user}用户删除了[流转历史操作表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R delete(String id){
//		hiOperateService.delete(id);//物理删除
//		    return R.success();
//	}
//
//	@PostMapping("/batchDelete")
//	//@Log(value = "#{@user}用户删除了[流转历史操作表]数据，删除参数为:", operationType = OperationType.DELETE, paramaType = ParamType.FORM)
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		hiOperateService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	//@Log(value = "#{@user}用户复制了[流转历史操作表]数据，使用参数为:", paramaType = ParamType.FORM)
//	public R copy(String id){
//		if (hiOperateService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
