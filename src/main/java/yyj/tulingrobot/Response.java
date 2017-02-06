package yyj.tulingrobot;

import com.alibaba.fastjson.JSONObject;

import yyj.util.BeanUtil;

public abstract class Response {
	/**响应类型-文本*/
	public static final String RESPONSE_TYPE_TEXT="100000";
	/**响应类型-链接*/
	public static final String RESPONSE_TYPE_LINK="200000";
	/**响应类型-新闻*/
	public static final String RESPONSE_TYPE_NEWS="302000";
	/**响应类型-菜谱*/
	public static final String RESPONSE_TYPE_MENU="308000";
	/**响应类型-儿歌*/
	public static final String RESPONSE_TYPE_SONG="313000";
	/**响应类型-诗词*/
	public static final String RESPONSE_TYPE_POEM="314000";
	/**响应码*/
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return BeanUtil.staticToString(this);
	}
	public  Response fromStr(String str){
		BeanUtil.fromJsonStr(this, str);
		return this;
	}
	public static Response fromJsonStr(String str){
		JSONObject jobj=JSONObject.parseObject(str);
		switch (jobj.getString("code")) {
		case Response.RESPONSE_TYPE_TEXT:
			return new TextResponse().fromStr(str);
		case Response.RESPONSE_TYPE_LINK:
			if(str.contains("找到航班信息"))
				return new AirportResponse().fromStr(str);
			if(str.contains("找到列车信息"))
				return new TrainResponse().fromStr(str);
			return new LinkResponse().fromStr(str);
		case Response.RESPONSE_TYPE_NEWS:
			return new NewsResponse().fromStr(str);
		case Response.RESPONSE_TYPE_MENU:
			return new MenuResponse().fromStr(str);
		case Response.RESPONSE_TYPE_SONG:
			return new ChildSongResponse().fromStr(str);
		case Response.RESPONSE_TYPE_POEM:
			return new PoemResponse().fromStr(str);
		default:
			return null;
		}
	}
}

