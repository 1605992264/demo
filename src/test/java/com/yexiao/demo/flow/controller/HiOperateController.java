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
//		List<HiOperateDO>  hiOperateDOList = hiOperateService.queryPointProcessHistory(params, (String) params.get("systemCode"));
//		// 封装分页数据
//		JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", hiOperateDOList);
//        return R.success(jsonMap);
//	}
//
//    @PostMapping("/form")
//	public R form(@RequestParam(required = true)String id){
//        HiOperateDO hiOperate = hiOperateService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", hiOperate);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/save")
//	public R save(@Validated HiOperateDO hiOperate){
//		if (hiOperateService.save(hiOperate) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	public R remove(String id){
//		if (hiOperateService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		hiOperateService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	public R delete(String id){
//		if (hiOperateService.delete(id) > 0){//物理删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchDelete")
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		hiOperateService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	public R copy(String id){
//		if (hiOperateService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//}
