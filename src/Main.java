import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main {

	final static String token = "1a500fbfc4d14a21b63e829334440a99";

	private static void put(String filename, BufferedImage image) {

		AbstractHttpClient httpClient = new DefaultHttpClient();

		HttpPut method = new HttpPut(filename);

		method.setHeader("Authorization", "Bearer " + token);
		method.setHeader("Accept", "*/*");
		method.setHeader("Expect", "100-continue");
		method.setHeader("Content-Type", "image/png");

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			byte[] bytesOut = baos.toByteArray();

			ByteArrayEntity myEntity = new ByteArrayEntity(bytesOut);
			method.setEntity(myEntity);

			HttpResponse response = httpClient.execute(method);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void post(String filename) {

		AbstractHttpClient httpClient = new DefaultHttpClient();

		HttpPost method = new HttpPost(filename + "?publish");

		method.setHeader("Authorization", "Bearer " + token);
		method.setHeader("Accept", "*/*");

		try {
			HttpResponse response = null;
			response = httpClient.execute(method);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String filename = "https://webdav.yandex.ru/1111.png";

		Robot robot = null;

		try {
			robot = new Robot();
			Rectangle captureSize = new Rectangle(0, 0, 500, 500);
			BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
			put(filename, bufferedImage);
			post(filename);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
