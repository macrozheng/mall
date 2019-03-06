package com.macro.mall.util;


import java.util.UUID;

public class UUIDTools {
	/**
	 * 产生UUID随机码：随机码中有"-"分隔符   长度为36位
	 * @return
	 */
	public static String uuid( ) {
		return UUID.randomUUID().toString() ;
	}
	/**
	 * 产生UUID随机码：随机码中没有"-"分隔符  长度为32位
	 * @return
	 */
	public static String uuidStr() {
		return UUID.randomUUID().toString().replace("-", "") ;
	}	
}
