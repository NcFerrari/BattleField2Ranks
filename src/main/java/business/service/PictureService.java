package business.service;

import enums.PictureCategoryEnum;
import javafx.scene.image.Image;

import java.io.File;

public interface PictureService {

    Image getFXPicture(PictureCategoryEnum pictureCategory, String name);

    java.awt.Image getSwingPicture(PictureCategoryEnum pictureCategory, String name);

}
