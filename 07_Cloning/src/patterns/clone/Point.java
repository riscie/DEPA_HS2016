package patterns.clone;

import java.awt.Color;

public class Point implements Cloneable {
	private int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public String toString() {
		return String.format("Point(%f, %f)", x, y);
	}
}

class ColoredPoint extends Point {
	private Color color;

	public ColoredPoint(int x, int y, Color c) {
		super(x, y);
		this.color = c;
	}

	@Override
	public String toString() {
		return String.format("ColorPoint(%s, %s)", super.toString(), color);
	}

}
