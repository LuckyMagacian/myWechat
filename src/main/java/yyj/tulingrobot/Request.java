package yyj.tulingrobot;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import yyj.util.AppException;
import yyj.util.BeanUtil;
import yyj.util.CheckReplaceUtil;
import yyj.util.ConfigUtil;
import yyj.util.HttpUtil;

public class Request {	
	/**图灵Api密钥*/
	private String key;
	/**请求信息*/
	private String info;
	/**用户编号 信息不完整时可以通过该字段进入上下文模式*/
	private String userId;
	/**位置信息*/
	private String loc;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		StringBuffer buffer=new StringBuffer(this.getClass().getSimpleName()+":[");
		try {
			List<Method> getters=BeanUtil.getGetters(this);
			for(Method each:getters){
				if(!buffer.toString().endsWith("["))
					buffer.append(",");
				buffer.append(CheckReplaceUtil.firstCharLowcase(each.getName().substring(3))+"=");
				buffer.append(each.invoke(this));
			}
			buffer.append("]");
		} catch (Exception e) {
			throw new AppException("构建bean字符串信息异常",e);
		}
		return buffer.toString();
	}
	
	public String toJson(){
		return JSONObject.toJSONString(this);
	}
	public String postReq(){
		try{
			String url="http://www.tuling123.com/openapi/api?";
			url=ConfigUtil.get("tulinUrl")==null||ConfigUtil.get("tulinUrl").isEmpty()?url:ConfigUtil.get("tulinUrl");
	//		return HttpUtil.postStr(toJson(), url, "utf-8");
			Map<String, Field> map=BeanUtil.getFieldsNoStatic(this);
			StringBuffer buffer=new StringBuffer(url);
			for(Map.Entry<String, Field> each:map.entrySet()){
				String  name=each.getKey();
				Field   field=each.getValue();
				String  value=(String) field.get(this);
				if(value==null||value.equals(""))
					continue;
				if(buffer.lastIndexOf("?")==-1)
					buffer.append("?");
				if(buffer.charAt(buffer.length()-1)!='?')
					buffer.append('&');
					buffer.append(name+"="+value);
			}
			return HttpUtil.get(buffer.toString(), "utf-8");
		}catch (Exception e) {
			throw new AppException("请求图灵服务器异常!",e);
		}
	}
	
}
