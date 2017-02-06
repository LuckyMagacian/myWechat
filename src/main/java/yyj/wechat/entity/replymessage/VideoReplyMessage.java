package yyj.wechat.entity.replymessage;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import yyj.util.AppException;
/**
 * 微信消息类-自动消息类-自动回复视频消息类
 * @author 1
 *
 */
public class VideoReplyMessage extends WechatReplyMessage {
	/**
	 * 自动回复视频消息类-内部类-视频
	 * @author 1
	 *
	 */
	public static class Video{
		/**素材编号*/
		private String mediaId;		
		/**视频标题*/
		private String title;		
		/**视频描述*/
		private String description;	
		public String getMediaId() {
			return mediaId;
		}
		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public DOMElement toElement() {
			DOMElement element=new DOMElement("Music");
			element.addElement("Title").addCDATA(title);
			element.addElement("Description").addCDATA(description);
			element.addElement("MediaId").addCDATA(mediaId);
			return element;
		}
		public void fromElement(Element ele){
			setTitle(ele.elementText("Title"));
			setDescription(ele.elementText("Description"));
			setMediaId(ele.elementText("MediaId"));
		}
	}
	/**视频消息*/
	private Video video;
	public VideoReplyMessage() {
		setMsgType(AUTO_MESSAGE_TYPE_VIDEO);
		video=new Video();
	}
	public String getMediaId() {
		return video.mediaId;
	}
	public void setMediaId(String mediaId) {
		video.mediaId = mediaId;
	}
	public String getTitle() {
		return video.title;
	}
	public void setTitle(String title) {
		video.title = title;
	}
	public String getDescription() {
		return video.description;
	}
	public void setDescription(String description) {
		video.description = description;
	}
	@Override
	public DOMElement toElement() {
		DOMElement element=super.toElement();
		element.add(video.toElement());
		return element;
	}
	@Override
	public void fromString(String str) {
		try{
			super.fromString(str);
			Element root=DocumentHelper.parseText(str).getRootElement();
			video.fromElement(root.element("Video"));
		}catch (Exception e) {
			throw new AppException("从xml中提取属性异常",e);
		}
	}
	
}
