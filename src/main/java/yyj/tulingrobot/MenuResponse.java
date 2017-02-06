package yyj.tulingrobot;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import yyj.util.AppException;
import yyj.util.BeanUtil;

public class MenuResponse extends TextResponse {
	/**
	 * 菜谱响应内部类-菜谱
	 * @author 1
	 *
	 */
	public static class Menu{
		/**菜名*/
		private String name;
		/**图标链接*/
		private String icon;
		/**材料信息*/
		private String info;
		/**详情url*/
		private String detailurl;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getDetailurl() {
			return detailurl;
		}
		public void setDetailurl(String detailurl) {
			this.detailurl = detailurl;
		}
		@Override
		public String toString() {
			return BeanUtil.staticToString(this);
		}
		public Menu fromJson(JSONObject jobj){
			BeanUtil.fromJsonStr(this, jobj.toJSONString());
			return this;
		}
	}
	/**新闻列表*/
	private List<Menu> list;
	public MenuResponse() {
		list=new ArrayList<>();
	}
	public List<Menu> getList() {
		return list;
	}
	public void setList(List<Menu> list) {
		this.list = list;
	}
	public void addMenu(Menu menu){
		list.add(menu);
	}
	@Override
	public MenuResponse fromStr(String str){
		try {
			JSONObject jobj=JSONObject.parseObject(str);
			setCode(jobj.getString("code"));
			setText(jobj.getString("text"));
			JSONArray jArray=jobj.getJSONArray("list");
			for(int i=0;i<jArray.size();i++)
				list.add(new MenuResponse.Menu().fromJson(jArray.getJSONObject(i)));
			return this;
		} catch (Exception e) {
			throw new AppException("字符串转对象异常!",e);
		}
	}
}
