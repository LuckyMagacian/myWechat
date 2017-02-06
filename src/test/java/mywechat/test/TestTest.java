package mywechat.test;

import org.junit.Test;

import yyj.tulingrobot.Request;
import yyj.tulingrobot.Response;
import yyj.util.ConfigUtil;
import yyj.util.HttpUtil;

public class TestTest {
	@Test
	public void test1(){ 
		Request req=new Request();
		req.setInfo("北京到上海火车");
		req.setKey(ConfigUtil.get("tulinKey"));
		req.setUserId("123456789");
		String rs=req.postReq();
		System.out.println(Response.fromJsonStr(rs));
	}
	@Test
	public void test2(){
		String str="{"+

                
"\"key\": \"47478f1234434e4f966cbf62e220852f\","+

                
"\"info\": \"今天天气怎么样\"，"+

                
"\"loc\"：\"北京市中关村\"，"+

                
"\"userid\"：\"123456\""+

                "}";
		StringBuffer buffer=new StringBuffer(str) ;
		System.out.println(buffer.lastIndexOf("?"));
		System.out.println(HttpUtil.postStr(str, "http://www.tuling123.com/openapi/api","utf-8"));
	}
	
	
}
