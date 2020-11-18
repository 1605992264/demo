//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.ProcessNodeDO;
//import com.txdata.flow.service.ProcessNodeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// * 流程节点表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:26
// */
//@RestController
//@RequestMapping("/processNode")
//public class ProcessNodeController extends BaseController {
//	@Autowired
//	private ProcessNodeService processNodeService;
//
//	@PostMapping("/list")
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<ProcessNodeDO> page = new Page<ProcessNodeDO>(query.getPageNo(), query.getPageSize());
//		page = processNodeService.page(page, query);
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
//        ProcessNodeDO processNode = processNodeService.get(id);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("formObject", processNode);
//        return R.success(jsonMap);
//    }
//
//	@PostMapping("/remove")
//	public R remove(String id){
//		if (processNodeService.remove(id) > 0){ //逻辑删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	public R batchRemove(@RequestParam("ids[]") String[] ids){
//		processNodeService.batchRemove(ids);//批量逻辑删除
//		return R.success();
//	}
//
//	@PostMapping("/delete")
//	public R delete(String id){
//		if (processNodeService.delete(id) > 0){//物理删除
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchDelete")
//	public R batchDelete(@RequestParam("ids[]") String[] ids){
//		processNodeService.batchDelete(ids);//批量物理删除
//		return R.success();
//	}
//
//	@PostMapping("/copy")
//	public R copy(String id){
//		if (processNodeService.copy(id) > 0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	/**
//	 * 根据发起人Id与当前流程id查询具体人员
//	 * @param user 发起人Id
//	 * @param nodeId 当前流程id
//	 * @return
//	 */
////	@PostMapping("/queryParticularUsers")
////	public R queryParticularUsers(String user, String nodeId){
////		//String userNameChain = processNodeService.queryParticularUsers(user,nodeId);
////		JSONObject jsonObject = new JSONObject();
////		if (StringUtils.isNotBlank(userNameChain)){
////			jsonObject.put("userNameChain",userNameChain);
////			return R.success(jsonObject);
////		}else {
////			return R.error("401","找不到对应节点的具体人员");
////		}
////	}
//}
//
