//package com.yexiao.demo.flow.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.FuserDO;
//import com.txdata.flow.service.FuserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 第三方用户表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:33:54
// */
//@Controller
//@RequestMapping("/flow/fuser")
//public class FuserController extends BaseController {
//	@Autowired
//	private FuserService fuserService;
//
//	@ResponseBody
//	@PostMapping("/list")
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<FuserDO> page = new Page<FuserDO>(query.getPageNo(), query.getPageSize());
//		page = fuserService.page(page, query);
//		// 封装分页数据
//		Map<String,Object> jsonMap = new HashMap<String,Object>();
//        jsonMap.put("rows", page.getRecords());
//        jsonMap.put("pageSize", page.getSize());
//        jsonMap.put("pageNo", page.getCurrent());
//        jsonMap.put("count", page.getTotal());
//        return R.success(jsonMap);
//	}
//
//    @ResponseBody
//    @PostMapping("/form")
//    public R form(@RequestParam(required = true)String id , String systemCode){
//        FuserDO fuser = fuserService.get(id,systemCode);
//        Map<String,Object> jsonMap = new HashMap<String,Object>();
//        jsonMap.put("fuser", fuser);
//        return R.success(jsonMap);
//    }
//
//	@ResponseBody
//	@PostMapping("/save")
//	public R save(FuserDO fuser){
//		if(fuserService.save(fuser)>0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@ResponseBody
//	@PostMapping("/update")
//	public R update(FuserDO fuser){
//		if(fuserService.update(fuser)>0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	@ResponseBody
//	public R remove(String id , String systemCode){
//		if(fuserService.remove(id,systemCode)>0){
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	@ResponseBody
//	public R remove(@RequestParam("ids") String[] ids , String systemCode){
//		fuserService.batchRemove(ids , systemCode);
//		return R.success();
//	}
//
//}
