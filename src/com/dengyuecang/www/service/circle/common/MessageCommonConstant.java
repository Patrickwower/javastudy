package com.dengyuecang.www.service.circle.common;


import com.dengyuecang.www.service.common.CommonConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础信息类
 * @author acang
 *
 */
public class MessageCommonConstant {

	public static Map<String,String> MESSAGE_TYPE_DETAIL = new HashMap<String,String>();


	/**
	 * type类型
	 */
	public static String TYPE_ZAN = "ZAN";

	public static String TYPE_FOLLOW = "FOLLOW";

	public static String TYPE_COMMENT = "COMMENT";

	public static String TYPE_REPLY = "REPLY";

	static {

		MESSAGE_TYPE_DETAIL.put(TYPE_ZAN,"赞了你");

		MESSAGE_TYPE_DETAIL.put(TYPE_COMMENT,"评论了你");

		MESSAGE_TYPE_DETAIL.put(TYPE_FOLLOW,"关注了你");

		MESSAGE_TYPE_DETAIL.put(TYPE_REPLY,"回复了你");

	}


}
