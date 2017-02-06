package yyj.wechat.manageer;
/**
 * 微信消息管理类
 * @author 1
 *
 */
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import yyj.util.AppException;

public class MessageManager {
	
	private MessageManager(){
		
	}
	
	public static void deal(String message){
		Element element=toElement(message);
		if(element.selectSingleNode("//eventKey")!=null)
			MenuEventMessageManager.deal(element);
		if(element.selectSingleNode("//event")!=null)
			EventMessageManager.deal(element);
		if(element.selectSingleNode("//messageId")!=null)
			EventMessageManager.deal(element);
	}
	
	private static Element toElement(String messageXml){
		try{
			Element element=DocumentHelper.parseText(messageXml).getRootElement();
			return element;
		}catch (Exception e) {
			throw new AppException("微信消息解析为xml异常",e);
		}
	}
	
	public static class CommonMessageManager{
		public static void deal(Element element){
			//TODO deal message
		}
	}
	public static class EventMessageManager{
		public static void deal(Element element){
			//TODO deal message
		}
	}
	public static class MenuEventMessageManager{
		public static void deal(Element element){
			//TODO deal message	
		}
	}
}
