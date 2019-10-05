/**
 * 
 */
package com.btsoft.jf.cloud.core.constant;

/**
 * @author bchen
 * 请求常量类
 */
public interface ControllerContants {
	
	public interface Path{
		public final static String 	CREATE="/create";
		public final static String 	SINGLE="/single";
		public final static String 	SINGLE_ID="/single/{id}";
		public final static String 	UPDATE="/update";
		public final static String 	DELETE="/delete";
		public final static String 	DELETE_ID="/delete/{id}";
		public final static String 	BATCH_DELETE="/batch/delete";
		public final static String 	LIST="/list";
		public final static String 	PAGE="/page";
		public final static String 	SYNC="/sync";
		public final static String 	SAVE="/save";
	}
	
	public interface Api{
		public final static String 	CREATE="创建单个对象";
		public final static String 	SINGLE="获取单个对象";
		public final static String 	UPDATE="更新单个对象";
		public final static String 	DELETE="删除单个对象";
		public final static String 	BATCH_DELETE="删除多个对象";
		public final static String 	LIST="获取对象列表";
		public final static String 	PAGE="分页获取对象列表";
		public final static String 	SAVE="保存单个对象";
	}
	
	public interface Resource{
		public final static String 	COMMON="common";
	}
	
	public interface Operator{
		public final static String 	CREATE="create";
		public final static String 	SINGLE="single";
		public final static String 	UPDATE="update";
		public final static String 	DELETE="delete";
		public final static String 	BATCH_DELETE="batchDelete";
		public final static String 	LIST="list";
		public final static String 	PAGE="page";
		public final static String 	SYNC="sync";
		public final static String 	SAVE="save";
	}
}
