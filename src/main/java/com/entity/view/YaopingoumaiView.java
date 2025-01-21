package com.entity.view;

import com.entity.YaopingoumaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 药品购买
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-05-06 15:06:22
 */
@TableName("yaopingoumai")
public class YaopingoumaiView  extends YaopingoumaiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YaopingoumaiView(){
	}
 
 	public YaopingoumaiView(YaopingoumaiEntity yaopingoumaiEntity){
 	try {
			BeanUtils.copyProperties(this, yaopingoumaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
