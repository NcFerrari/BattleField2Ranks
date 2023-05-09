package business.service;

import javafx.scene.image.Image;

import java.io.File;

public interface PictureService {

    Image getFXPicture(String path);

    java.awt.Image getSwingPicture(String path);

    File getImage(String path);
}
