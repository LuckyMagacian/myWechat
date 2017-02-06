package yyj.tulingrobot;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import yyj.util.AppException;
import yyj.util.BeanUtil;

public class NewsResponse extends TextResponse {
	/**
	 * 新闻响应内部类-新闻类
	 * @author 1
	 *
	 */
	public static class Article{
		/**标题*/
		private String article;
		/**来源*/
		private String source;
		/**图标*/
		private String icon;
		/**详细链接*/
		private String detailurl;
		public String getArticle() {
			return article;
		}
		public void setArticle(String article) {
			this.article = article;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getDetailurl() {
			return detailurl;
		}
		public void setDetailurl(String detailurl) {
			this.detailurl = detailurl;
		}
		public String toString(){
			return BeanUtil.staticToString(this);
		}
		public Article fromJson(JSONObject jobj){
			BeanUtil.fromJsonStr(this, jobj.toJSONString());
			return this;
		}
	}
	/**新闻列表*/
	private List<Article> list;
	public NewsResponse() {
		list=new ArrayList<>();
	}
	public List<Article> getList() {
		return list;
	}
	public void setList(List<Article> list) {
		this.list = list;
	}
	public void addAtricle(NewsResponse.Article article){
		list.add(article);
	}
	@Override
	public NewsResponse fromStr(String str){
		try {
			JSONObject jobj=JSONObject.parseObject(str);
			setCode(jobj.getString("code"));
			setText(jobj.getString("text"));
			JSONArray jArray=jobj.getJSONArray("list");
			for(int i=0;i<jArray.size();i++)
				list.add(new NewsResponse.Article().fromJson(jArray.getJSONObject(i)));
			return this;
		} catch (Exception e) {
			throw new AppException("字符串转对象异常!",e);
		}

	}
}
