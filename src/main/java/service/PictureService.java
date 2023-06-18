package service;

import enums.PictureCategoryEnum;
//import javafx.scene.image.Image;

public interface PictureService {

//    Image getFXPicture(PictureCategoryEnum pictureCategory, String name);

    java.awt.Image getSwingPicture(PictureCategoryEnum pictureCategory, String name);

}
