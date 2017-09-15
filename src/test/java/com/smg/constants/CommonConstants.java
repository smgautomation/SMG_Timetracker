package com.smg.constants;

import com.smg.utils.PropertyUtil;

public class CommonConstants {
	public static final int DEFAULT_WEBDRIVER_TIMEOUT = Integer.parseInt(PropertyUtil.getProp("src/test/resources/properties/config.properties", "webdriver.default.time"));
	public static final int DEFAULT_IMPLICIT_TIMEOUT = Integer.parseInt(PropertyUtil.getProp("src/test/resources/properties/config.properties", "implicit.default.time"));
}
