package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.YaopingoumaiDao;
import com.entity.YaopingoumaiEntity;
import com.service.YaopingoumaiService;
import com.entity.vo.YaopingoumaiVO;
import com.entity.view.YaopingoumaiView;

@Service("yaopingoumaiService")
public class YaopingoumaiServiceImpl extends ServiceImpl<YaopingoumaiDao, YaopingoumaiEntity> implements YaopingoumaiService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YaopingoumaiEntity> page = this.selectPage(
                new Query<YaopingoumaiEntity>(params).getPage(),
                new EntityWrapper<YaopingoumaiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YaopingoumaiEntity> wrapper) {
		  Page<YaopingoumaiView> page =new Query<YaopingoumaiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<YaopingoumaiVO> selectListVO(Wrapper<YaopingoumaiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YaopingoumaiVO selectVO(Wrapper<YaopingoumaiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YaopingoumaiView> selectListView(Wrapper<YaopingoumaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YaopingoumaiView selectView(Wrapper<YaopingoumaiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
