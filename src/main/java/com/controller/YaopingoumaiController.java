package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.YaopingoumaiEntity;
import com.entity.view.YaopingoumaiView;

import com.service.YaopingoumaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 药品购买
 * 后端接口
 * @author 
 * @email 
 * @date 2022-05-06 15:06:22
 */
@RestController
@RequestMapping("/yaopingoumai")
public class YaopingoumaiController {
    @Autowired
    private YaopingoumaiService yaopingoumaiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YaopingoumaiEntity yaopingoumai, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			yaopingoumai.setZhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			yaopingoumai.setYishengzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<YaopingoumaiEntity> ew = new EntityWrapper<YaopingoumaiEntity>();
		PageUtils page = yaopingoumaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopingoumai), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YaopingoumaiEntity yaopingoumai, 
		HttpServletRequest request){
        EntityWrapper<YaopingoumaiEntity> ew = new EntityWrapper<YaopingoumaiEntity>();
		PageUtils page = yaopingoumaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopingoumai), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YaopingoumaiEntity yaopingoumai){
       	EntityWrapper<YaopingoumaiEntity> ew = new EntityWrapper<YaopingoumaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yaopingoumai, "yaopingoumai")); 
        return R.ok().put("data", yaopingoumaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YaopingoumaiEntity yaopingoumai){
        EntityWrapper< YaopingoumaiEntity> ew = new EntityWrapper< YaopingoumaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yaopingoumai, "yaopingoumai")); 
		YaopingoumaiView yaopingoumaiView =  yaopingoumaiService.selectView(ew);
		return R.ok("查询药品购买成功").put("data", yaopingoumaiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YaopingoumaiEntity yaopingoumai = yaopingoumaiService.selectById(id);
        return R.ok().put("data", yaopingoumai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YaopingoumaiEntity yaopingoumai = yaopingoumaiService.selectById(id);
        return R.ok().put("data", yaopingoumai);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YaopingoumaiEntity yaopingoumai, HttpServletRequest request){
    	yaopingoumai.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yaopingoumai);

        yaopingoumaiService.insert(yaopingoumai);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YaopingoumaiEntity yaopingoumai, HttpServletRequest request){
    	yaopingoumai.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yaopingoumai);

        yaopingoumaiService.insert(yaopingoumai);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YaopingoumaiEntity yaopingoumai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yaopingoumai);
        yaopingoumaiService.updateById(yaopingoumai);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yaopingoumaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<YaopingoumaiEntity> wrapper = new EntityWrapper<YaopingoumaiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			wrapper.eq("zhanghao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			wrapper.eq("yishengzhanghao", (String)request.getSession().getAttribute("username"));
		}

		int count = yaopingoumaiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
