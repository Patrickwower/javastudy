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
public class InterestBarCommonConstant {

//	public static final String MODEL_NAME = "";

	/**
	 * banner状态
	 */
	public static final List<String> INTEREST_BAR_COVER = new ArrayList<String>();

	static {

		INTEREST_BAR_COVER.add(CommonConstant.STATIC_URL+"/interestbar/cover1.jpg");

		INTEREST_BAR_COVER.add(CommonConstant.STATIC_URL+"/interestbar/cover2.jpg");

		INTEREST_BAR_COVER.add(CommonConstant.STATIC_URL+"/interestbar/cover3.jpg");

		INTEREST_BAR_COVER.add(CommonConstant.STATIC_URL+"/interestbar/cover4.jpg");

		INTEREST_BAR_COVER.add(CommonConstant.STATIC_URL+"/interestbar/cover5.jpg");
	}


}
