package com.pack;

/**
 * 解包常量类接口
 * @author Administrator
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public interface PackInterface {

	public final String CHARSET = "gbk";

	public final String SEPARATOR = "\u0000";

	public final String STARTSEPARATOR = "\u0007";

	public final String ENDSEPARATOR = "\u0008";
	
	public final String SEPARATORAS = "\u0001";
	
	public final String SEAS = SEPARATOR+SEPARATORAS;

}