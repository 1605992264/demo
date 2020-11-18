//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.FlowProcessDO;
//import com.txdata.flow.service.FlowProcessService;
//import com.txdata.flow.service.OrderService;
//import com.txdata.flow.service.OrderTaskService;
//import com.txdata.flow.service.ProcessNodeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
///**
// * 流程定义表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:14
// */
//@RestController
//@RequestMapping("/process")
//public class FlowProcessController extends BaseController {
//	@Autowired
//	private FlowProcessService processService;
//	@Autowired
//	private ProcessNodeService processNodeService;
//	@Autowired
//	private OrderService orderService;
//	@Autowired
//	private OrderTaskService orderTaskService;
//
//
//	@PostMapping("/list")
//	@ResponseBody
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<FlowProcessDO> page = new Page<FlowProcessDO>(query.getPageNo(), query.getPageSize());
//		page = processService.page(page, query);
//		// 封装分页数据
//		JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", page.getRecords());
//        jsonMap.put("pageSize", page.getSize());
//        jsonMap.put("pageNo", page.getCurrent());
//        jsonMap.put("count", page.getTotal());
//        return R.success(jsonMap);
//	}
//
//	/**
//	 * 流程查看
//	 * @param processCode
//	 * @param version
//	 * @return
//	 */
//    @PostMapping("/edit")
//	@ResponseBody
//    public R edit(String processCode, int version){
//        FlowProcessDO process = processService.form(processCode,version);
//        String originalData = process.getOriginal();
//        JSONObject structuralData = JSON.parseObject(originalData);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("structuralData", structuralData);
//        return R.success(jsonMap);
//    }
//
//	/**
//	 * 当前报销单流程节点位置
//	 * @param processId 该processId代表流程实例id
//	 * @return
//	 */
//	@PostMapping("/form")
//	@ResponseBody
//	public R form(String processId){
//		JSONObject jsonMap = processService.form(processId);
//		return R.success(jsonMap);
//	}
//
//	/**
//	 * 删除流程
//	 * @param processId
//	 * @return
//	 */
//	@PostMapping("/remove")
//	@ResponseBody
//	public R remove(String processId){
//		if (processService.removeWithChain(processId) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		processService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	public R delete(String id){
//		if (processService.delete(id) > 0){//物理删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchDelete")
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		processService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	public R copy(String id){
//		if (processService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	/**
//	 * 流程保存
//	 * @param structuralData
//	 * @return
//	 */
//	@PostMapping("/ruleSave")
//	@ResponseBody
//	public R ruleSave(String processId, String structuralData){
////		if (StringUtils.isNotBlank(processId)){
////			//判断当前修改的版本如果小于最大版本,历史版本不让修改
////			if (processService.get(processId).getVersion() < processService.queryMaxVersion(processId)){
////				return R.error("401","当前流程版本低于最大版本,为历史版本,无法进行修改");
////			}
////		}
//		processNodeService.ruleSave(structuralData,processId);
//		return R.success();
//	}
//
//	/**
//	 * 通过流程编号查询最新版本的流程
//	 * @param processCode
//	 * @return
//	 */
//	@PostMapping("/newVersionProcess")
//	@ResponseBody
//	public R queryMaxVersionInfo(String processCode){
//		FlowProcessDO flowProcessDO = processService.queryMaxVersionInfo(processCode);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("formObject",flowProcessDO);
//		return R.success(jsonObject);
//	}
//
//	/**
//	 * 按照指定的返回格式查询所有的流程信息
//	 * @param params
//	 * @return
//	 */
//	@PostMapping("/getAllProcess")
//	@ResponseBody
//	public R getAllProcess(@RequestParam Map<String, Object> params){
//		//查询列表数据
//		Query query = new Query(params);
//		Page<JSONObject> page = new Page<JSONObject>(query.getPageNo(), query.getPageSize());
//		page = processService.queryAllProcess(page, query);
//		// 封装分页数据
//		JSONObject jsonMap = new JSONObject();
//		jsonMap.put("rows", page.getRecords());
//		jsonMap.put("pageSize", page.getSize());
//		jsonMap.put("pageNo", page.getCurrent());
//		jsonMap.put("count", page.getTotal());
//		return R.success(jsonMap);
//	}
//
//	/**
//	 * 查询单一流程明细
//	 * @param processId
//	 * @return
//	 */
//	@PostMapping("/singleForm")
//	public R singleForm(String processId){
//		FlowProcessDO flowProcessDO = processService.get(processId);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("formObject",flowProcessDO);
//		return R.success(jsonObject);
//	}
//
//	/**
//	 * 查看历史版本(单个)
//	 * @param params
//	 * @return
//	 */
//	@PostMapping("/queryHisProcess")
//	public R queryHisProcess(@RequestParam Map<String,Object> params){
//		JSONObject jsonObject = processService.queryHisProcess(params);
//		JSONObject newJsonObject = new JSONObject();
//		newJsonObject.put("formObject",jsonObject);
//		return R.success(newJsonObject);
//	}
//
//	/**
//	 * 查看历史版本(单个)
//	 * @param params
//	 * @return
//	 */
//	@PostMapping("/queryHisProcessList")
//	public R queryHisProcessList(@RequestParam Map<String,Object> params){
//		//查询列表数据
//		Query query = new Query(params);
//		Page<JSONObject> page = new Page<JSONObject>(query.getPageNo(), query.getPageSize());
//		page = processService.queryHisProcessList(page, query);
//		// 封装分页数据
//		JSONObject jsonMap = new JSONObject();
//		jsonMap.put("rows", page.getRecords());
//		jsonMap.put("pageSize", page.getSize());
//		jsonMap.put("pageNo", page.getCurrent());
//		jsonMap.put("count", page.getTotal());
//		return R.success(jsonMap);
//	}
//
//	/**
//	 * 修改流程状态为“发布”状态
//	 * @param processCode
//	 * @param version
//	 * @return
//	 */
//	@PostMapping("/updateProcessState")
//	public R updateProcessState(String processCode, String version){
//		if (processService.updateOtherProcessState(processCode) > 0){
//			if (processService.updateProcessState(processCode,version) > 0) {
//				return R.success();
//			}
//		}
//		return R.error();
//	}
//}
