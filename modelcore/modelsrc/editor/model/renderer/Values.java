package editor.model.renderer;

public class Values {
	public  static final Values instance= new Values();
	private int fontSize=20;
	private int margin=1;
	public int getFontSize() {
		return fontSize;
	}
	public int getMargin() {
		return margin;
	}
}
