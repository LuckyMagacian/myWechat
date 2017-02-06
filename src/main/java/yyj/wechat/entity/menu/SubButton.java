package yyj.wechat.entity.menu;
/**
 * 微信菜单类-按键类-子菜单按键
 * @author 1
 *
 */
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class SubButton implements WechatButton{
	/**子菜单名称*/
	private String name;				
	/**子菜单按键*/
	private List<BaseButton> subButton;	
	public SubButton() {
		subButton=new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BaseButton> getSubButton() {
		return subButton;
	}

	public void setSubButton(List<BaseButton> buttons) {
		this.subButton = buttons;
	}
	

	public void addButton(BaseButton button){
		subButton.add(button);
	}
	public String toJson(){
		return JSONObject.toJSONString(this).replaceFirst("subButton","sub_button");
	}
}
