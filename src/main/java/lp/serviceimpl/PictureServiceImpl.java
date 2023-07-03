package lp.serviceimpl;

import javafx.scene.image.Image;
import lp.enums.PictureCategoryEnum;
import lp.enums.TextEnum;
import lp.service.PictureService;

import java.io.InputStream;

public class PictureServiceImpl implements PictureService {

    @Override
    public Image getAwardImage(PictureCategoryEnum pictureCategory, int rankNumber) {
        return getImage(pictureCategory, String.valueOf(rankNumber));
    }

    @Override
    public Image getNoAwardImage() {
        return getImage(PictureCategoryEnum.NO_AWARD, TextEnum.EMPTY_STRING.getText());
    }

    private Image getImage(PictureCategoryEnum pictureCategory, String rank) {
        String stringBuilder = pictureCategory.getPath() +
                rank +
                pictureCategory.getSuffix();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(stringBuilder);
        if (inputStream != null) {
            return new Image(inputStream);
        }
        return null;
    }
}
