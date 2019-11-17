/**
 * 
 */
package com.btsoft.jf.cloud.core.constant;

/**
 * @author bchen
 * 请求常量类
 */
public interface ControllerConstants {
	
	public interface Path{
		String 	CREATE="/create";
		String 	SINGLE="/single";
		String 	SINGLE_ID="/single/{id}";
		String 	UPDATE="/update";
		String 	DELETE="/delete";
		String 	DELETE_ID="/delete/{id}";
		String 	BATCH_DELETE="/batch/delete";
		String 	LIST="/list";
		String 	PAGE="/page";
		String 	SYNC="/sync";
		String 	SAVE="/save";
		String 	TREE="/tree";
		String 	SELECT="/select";
	}
	
	public interface Api{
		String 	CREATE="创建单个对象";
		String 	SINGLE="获取单个对象";
		String 	UPDATE="更新单个对象";
		String 	DELETE="删除单个对象";
		String 	BATCH_DELETE="删除多个对象";
		String 	LIST="获取对象列表";
		String 	PAGE="分页获取对象列表";
		String 	SAVE="保存单个对象";
	}
	
	public interface Resource{
		String 	COMMON="common";
	}
	
	public interface Operator{
		String 	CREATE="create";
		String 	SINGLE="single";
		String 	UPDATE="update";
		String 	DELETE="delete";
		String 	BATCH_DELETE="batchDelete";
		String 	LIST="list";
		String 	PAGE="page";
		String 	SYNC="sync";
		String 	SAVE="save";
		String 	TREE="tree";
	}
}
