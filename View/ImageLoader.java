package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {
    public static ImageIcon loadAsImageIcon(String imageName) throws IOException {
        URL imageURL = ImageLoader.class.getResource("/images/" + imageName);

        if (imageURL != null) {
            BufferedImage image = ImageIO.read(imageURL);
            return new ImageIcon(image);
        } else {
            throw new IOException("Image not found");
        }
    }

}
