package lp.service;

import lp.enums.PictureCategoryEnum;
import javafx.scene.image.Image;

public interface PictureService {

    Image getAwardImage(PictureCategoryEnum pictureCategory, int rankNumber);

    Image getNoAwardImage();

    Image getAwardImageById(int awardId, int level);

}
