package yyj.tulingrobot;

import com.alibaba.fastjson.JSONObject;

import yyj.util.AppException;
import yyj.util.BeanUtil;

public class PoemResponse extends TextResponse {
	public static class Function{
		/**诗歌名称*/
		private String name;
		/**作者*/
		private String author;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		@Override
		public String toString() {
			return BeanUtil.staticToString(this);
		}
		public Function fromJson(JSONObject jobj){
			BeanUtil.fromJsonStr(this, jobj.toJSONString());
			return this;
		}
	}
	private PoemResponse.Function function;
	public PoemResponse() {
		function=new PoemResponse.Function();
	}
	public PoemResponse.Function getFunction() {
		return function;
	}
	public void setFunction(PoemResponse.Function function) {
		this.function = function;
	}
	@Override
	public PoemResponse fromStr(String str){
		try {
			JSONObject jobj=JSONObject.parseObject(str);
			setCode(jobj.getString("code"));
			setText(jobj.getString("text"));
			setFunction(new PoemResponse.Function().fromJson(jobj.getJSONObject("function")));
			return this;
		} catch (Exception e) {
			throw new AppException("字符串转对象异常!",e);
		}
	}
}
