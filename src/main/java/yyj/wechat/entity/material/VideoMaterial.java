package yyj.wechat.entity.material;
/**
 * 微信素材类-永久素材-其他素材-视频素材
 * @author 1
 *
 */
public class VideoMaterial extends OterMaterial {
	/**视频标题*/
	private String title;			
	/**视频介绍*/
	private String introduction;	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
