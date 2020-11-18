//package com.yexiao.demo.flow.controller;
//
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.controller.BaseController;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.FajaxOffice;
//import com.txdata.flow.domain.FofficeDO;
//import com.txdata.flow.service.FofficeService;
//import com.txdata.system.utils.UserUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方机构表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:16
// */
//@Controller
//@RequestMapping("/flow/foffice")
//public class FofficeController extends BaseController {
//	@Autowired
//	private FofficeService fofficeService;
//
//	@ResponseBody
//	@PostMapping("/list")
//	public R list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//        Page<FofficeDO> page = new Page<FofficeDO>(query.getPageNo(), query.getPageSize());
//		page = fofficeService.page(page, query);
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
//        FofficeDO foffice = fofficeService.get(id,systemCode);
//        Map<String,Object> jsonMap = new HashMap<String,Object>();
//        jsonMap.put("foffice", foffice);
//        return R.success(jsonMap);
//    }
//
//	@ResponseBody
//	@PostMapping("/save")
//	public R save(FofficeDO foffice){
//		if(fofficeService.save(foffice)>0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@ResponseBody
//	@PostMapping("/update")
//	public R update(FofficeDO foffice){
//		if(fofficeService.update(foffice)>0){
//			return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/remove")
//	@ResponseBody
//	public R remove(String id , String systemCode){
//		if(fofficeService.remove(id,systemCode)>0){
//		    return R.success();
//		}
//		return R.error();
//	}
//
//	@PostMapping("/batchRemove")
//	@ResponseBody
//	public R remove(@RequestParam("ids") String[] ids , String systemCode){
//		fofficeService.batchRemove(ids,systemCode);
//		return R.success();
//	}
//
//	/**
//	 * 机构用户树
//	 *
//	 * @param isUser
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "treeUserData")
//	public String treeUserData(@RequestParam(required = false) String officeId, @RequestParam(required = false) Boolean isUser,
//							   @RequestParam(required = false) String systemCode, HttpServletResponse response) {
//		List<FajaxOffice> officeTree = (List<FajaxOffice>) UserUtils.getCache(UserUtils.CACHE_OFFICE_TREE);
//		if (officeTree == null || StrUtil.isNotBlank(officeId)) {
//			officeTree = new ArrayList<FajaxOffice>();
//			List<FofficeDO> rootList = new ArrayList<>();
//			if(StrUtil.isNotBlank(officeId)){
//				FofficeDO fofficeDO = new FofficeDO();
//				fofficeDO.setOfficeId(officeId);
//				fofficeDO.setSystemCode(systemCode);
//				rootList = fofficeService.searchList(fofficeDO);
//			}else {
//				rootList = UserUtils.getFofficeList(systemCode);
//			}
//
//			for (FofficeDO office : rootList) {
//				officeTree.add(new FajaxOffice(office, true));
//			}
//			officeTree = fofficeService.getNewOfficeTree(officeTree, isUser , systemCode);
//			UserUtils.putCache("fofficeUserTree", officeTree);
//		}
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		jsonMap.put("treeData", officeTree);
//		return renderString(response, R.success(jsonMap));
//	}
//
//}
