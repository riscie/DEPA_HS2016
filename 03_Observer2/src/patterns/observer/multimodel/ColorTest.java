package patterns.observer.multimodel;

import java.awt.Color;
import java.util.EnumSet;

import patterns.observer.multimodel.ColorModel.ColorChannel;

public class ColorTest {

	public static void main(String[] args) {
		final ColorModel model = new ColorModel(Color.BLACK);

		EnumSet<ColorChannel> all = EnumSet.allOf(ColorChannel.class);

		// the following listener changes red to gray
		model.addColorListener(c -> {
			if (Color.RED.equals(c)) {
				model.setRed(128);
				model.setGreen(128);
				model.setBlue(128);
			}
		}, all);

		model.addColorListener(c -> {
			System.out.println("Current color: " + c);
		}, all);

		model.setColor(Color.WHITE);
	}

}
