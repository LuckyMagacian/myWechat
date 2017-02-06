package yyj.wechat.entity.replymessage;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import yyj.util.AppException;
/**
 * 微信消息类-自动消息类-自动回复文本消息类
 * @author 1
 *
 */
public class TextReplyMessage extends WechatReplyMessage {
	/**文本内容*/
	private String content;	

	public TextReplyMessage() {
		setMsgType(AUTO_MESSAGE_TYPE_TEXT);
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public DOMElement toElement() {
		DOMElement element=super.toElement();
		element.addElement("Content").addCDATA(content);
		return element;
	}

	@Override
	public void fromString(String str) {
		try{
			super.fromString(str);
			Element root=DocumentHelper.parseText(str).getRootElement();
			setContent(root.elementText("Content"));
		}catch (Exception e) {
			throw new AppException("从xml中提取属性异常",e);
		}
	}
	
}
