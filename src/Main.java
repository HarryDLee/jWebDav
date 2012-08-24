import org.apache.http.HttpResponse;
import ru.arturgspb.jwebdav.auth.OAuth;
import ru.arturgspb.jwebdav.jWebDav;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main {

	final static String token = "1a500fbfc4d14a21b63e829334440a99";


	public static void main(String[] args) {

		String url = "https://webdav.yandex.ru/222.png";

		jWebDav webdav = new jWebDav(new OAuth("Bearer", token));

		Robot robot = null;

		try {
//			HttpResponse response3 = webdav.createDirectory("https://webdav.yandex.ru/my_dir/");
//			System.out.println(response3);
//			System.exit(1);

			robot = new Robot();
			Rectangle captureSize = new Rectangle(0, 0, 200, 500);
			BufferedImage bufferedImage = robot.createScreenCapture(captureSize);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", baos);
			byte[] bytesOut = baos.toByteArray();
			HttpResponse response = webdav.put(url, bytesOut, "image/png");
			if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_CREATED) {
				System.out.println("Created...");
//				HttpResponse response2 = webdav.post(url + "?publish");
//				System.out.println(response2.getLastHeader("Location").getValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
