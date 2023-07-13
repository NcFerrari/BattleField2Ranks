package lp.serviceimpl;

import javafx.scene.image.Image;
import lp.enums.PictureCategoryEnum;
import lp.enums.TextEnum;
import lp.service.PictureService;

import java.io.InputStream;

public class PictureServiceImpl implements PictureService {

    @Override
    public Image getAwardImage(PictureCategoryEnum pictureCategory, int rankNumber) {
        return getAwardImage(pictureCategory, rankNumber, 0);
    }

    private Image getAwardImage(PictureCategoryEnum pictureCategory, int rankNumber, int level) {
        switch (level) {
            case 1:
                pictureCategory = PictureCategoryEnum.BADGES_BRONZE;
                break;
            case 2:
                pictureCategory = PictureCategoryEnum.BADGES_SILVER;
                break;
            case 3:
                pictureCategory = PictureCategoryEnum.BADGES_GOLD;
                break;
            default:
        }
        return getImage(pictureCategory, String.valueOf(rankNumber));
    }

    @Override
    public Image getNoAwardImage() {
        return getAwardImageById(-1, 0);
    }

    @Override
    public Image getAwardImageById(int awardId, int level) {
        if (awardId < 2020419) {
            switch (awardId) {
                case 1031105:
                    return getAwardImage(null, 1, level);
                case 1031109:
                    return getAwardImage(null, 2, level);
                case 1031113:
                    return getAwardImage(null, 3, level);
                case 1031115:
                    return getAwardImage(null, 4, level);
                case 1031119:
                    return getAwardImage(null, 5, level);
                case 1031120:
                    return getAwardImage(null, 6, level);
                case 1031121:
                    return getAwardImage(null, 7, level);
                case 1031406:
                    return getAwardImage(null, 8, level);
                case 1031619:
                    return getAwardImage(null, 9, level);
                case 1031923:
                    return getAwardImage(null, 10, level);
                case 1032415:
                    return getAwardImage(null, 11, level);
                case 1190304:
                    return getAwardImage(null, 12, level);
                case 1190507:
                    return getAwardImage(null, 13, level);
                case 1190601:
                    return getAwardImage(null, 14, level);
                case 1191819:
                    return getAwardImage(null, 15, level);
                case 1220104:
                    return getAwardImage(null, 16, level);
                case 1220118:
                    return getAwardImage(null, 17, level);
                case 1220122:
                    return getAwardImage(null, 18, level);
                case 1220803:
                    return getAwardImage(null, 19, level);
                case 1222016:
                    return getAwardImage(null, 20, level);
                default:
                    return getImage(PictureCategoryEnum.NO_AWARD, TextEnum.EMPTY_STRING.getText());
            }
        } else if (awardId < 3040109 || awardId == 3270519) {
            switch (awardId) {
                case 2020419:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 1);
                case 2020719:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 2);
                case 2020903:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 3);
                case 2020913:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 4);
                case 2020919:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 5);
                case 2021322:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 6);
                case 2021403:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 7);
                case 2021613:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 8);
                case 2051902:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 9);
                case 2051907:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 10);
                case 2051919:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 11);
                case 2190303:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 12);
                case 2190308:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 13);
                case 2190309:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 14);
                case 2190318:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 15);
                case 2190703:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 16);
                case 2191319:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 17);
                case 2191608:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 18);
                case 3270519:
                    return getAwardImage(PictureCategoryEnum.MEDALS, 19);
                default:
                    return getImage(PictureCategoryEnum.NO_AWARD, TextEnum.EMPTY_STRING.getText());
            }
        } else {
            switch (awardId) {
                case 3040109:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 1);
                case 3040718:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 2);
                case 3150914:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 3);
                case 3151920:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 4);
                case 3190105:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 5);
                case 3190118:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 6);
                case 3190318:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 7);
                case 3190409:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 8);
                case 3190605:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 9);
                case 3190803:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 10);
                case 3191305:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 11);
                case 3211305:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 12);
                case 3212201:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 13);
                case 3240102:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 14);
                case 3240301:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 15);
                case 3240703:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 16);
                case 3241213:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 17);
                case 3242303:
                    return getAwardImage(PictureCategoryEnum.RIBBONS, 18);
                default:
                    return getImage(PictureCategoryEnum.NO_AWARD, TextEnum.EMPTY_STRING.getText());
            }
        }
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
