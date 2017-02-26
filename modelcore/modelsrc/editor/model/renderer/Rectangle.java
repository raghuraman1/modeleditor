package editor.model.renderer;

public class Rectangle {
	int x;
	int y;
	int width;
	int height;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getX1() {
		return x+width;
	}
	public int getY1() {
		return y+height;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	

}
