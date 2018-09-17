package com.pack;

/**
 * 解包常量类接口
 */
public interface PackInterface {

	public final String CHARSET = "gbk";

	public final String SEPARATOR = "\u0000";

	public final String STARTSEPARATOR = "\u0007";

	public final String ENDSEPARATOR = "\u0008";
	
	public final String SEPARATORAS = "\u0001";
	
	public final String SEAS = SEPARATOR+SEPARATORAS;

}