package yyj.wechat.entity.menuevent;

import org.dom4j.DocumentHelper;
import org.dom4j.dom.DOMElement;

import yyj.util.AppException;
import yyj.wechat.entity.event.WechatEventMessage;

public abstract class WechatMenuEvent extends WechatEventMessage {
	/**事件键值*/
	private String eventKey;
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	public  DOMElement    toElement(){
		DOMElement element=super.toElement();
		element.addElement("EventKey").addCDATA(eventKey);
		return element;
	}
	public void fromString(String str){
		try{
			super.fromString(str);
			setEvent(DocumentHelper.parseText(str).getRootElement().elementText("EventKey"));
		}catch (Exception e) {
			throw new AppException("从xml中提取属性异常",e);
		}
	}
	
}
