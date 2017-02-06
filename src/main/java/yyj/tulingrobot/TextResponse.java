package yyj.tulingrobot;

public class TextResponse extends Response {
	/**回复的文本信息*/
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
