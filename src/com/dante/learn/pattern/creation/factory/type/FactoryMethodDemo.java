package com.dante.learn.pattern.creation.factory.type;

interface ImageReader {
	DecodedImage getDecodeImage();
}

class DecodedImage {
	private String image;

	public DecodedImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return image + ": is decoded";
	}
}

class GifReader implements ImageReader {
	private DecodedImage decodedImage;

	public GifReader(String image) {
		this.decodedImage = new DecodedImage(image);
	}

	@Override
	public DecodedImage getDecodeImage() {
		System.out.println("I'm JpegReader");
		return decodedImage;
	}
}

class JpegReader implements ImageReader {
	private DecodedImage decodedImage;

	public JpegReader(String image) {
		decodedImage = new DecodedImage(image);
	}

	@Override
	public DecodedImage getDecodeImage() {
		System.out.println("I'm JpegReader");
		return decodedImage;
	}
}

public class FactoryMethodDemo {
	public static void main(String[] args) {
		DecodedImage decodedImage;
		ImageReader reader = null;
		String image = "test.jpeg";
		String format = image.substring(image.indexOf('.') + 1,
				(image.length()));
		if (format.equals("gif")) {
			reader = new GifReader(image);
		}
		if (format.equals("jpeg")) {
			reader = new JpegReader(image);
		}
		assert reader != null;
		decodedImage = reader.getDecodeImage();
		System.out.println(decodedImage);
	}
}
