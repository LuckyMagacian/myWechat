package yyj.tulingrobot;

import com.alibaba.fastjson.JSONObject;

import yyj.util.AppException;
import yyj.util.BeanUtil;

public class ChildSongResponse extends TextResponse {
	/**
	 * 儿歌响应内部类-功能
	 * @author 1
	 *
	 */
	public static class Function{
		/**歌名*/
		private String song;
		/**歌手*/
		private String singer;
		public String getSong() {
			return song;
		}
		public void setSong(String song) {
			this.song = song;
		}
		public String getSinger() {
			return singer;
		}
		public void setSinger(String singer) {
			this.singer = singer;
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
	/**功能*/
	private ChildSongResponse.Function function;
	public ChildSongResponse() {
		function=new ChildSongResponse.Function();
	}
	public ChildSongResponse.Function getFunction() {
		return function;
	}
	public void setFunction(ChildSongResponse.Function function) {
		this.function = function;
	}
	@Override
	public ChildSongResponse fromStr(String str){
		try {
			JSONObject jobj=JSONObject.parseObject(str);
			setCode(jobj.getString("code"));
			setText(jobj.getString("text"));
			setFunction(new ChildSongResponse.Function().fromJson(jobj.getJSONObject("function")));
			return this;
		} catch (Exception e) {
			throw new AppException("字符串转对象异常!",e);
		}
	}
}
