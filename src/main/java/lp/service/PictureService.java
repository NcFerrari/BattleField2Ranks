package lp.service;

import lp.enums.PictureCategoryEnum;
import javafx.scene.image.Image;

public interface PictureService {

    Image getFXPicture(PictureCategoryEnum pictureCategory, String name);

}
