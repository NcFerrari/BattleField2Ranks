package serviceimpl;

import enums.PictureCategoryEnum;
import javafx.scene.image.Image;
import service.PictureService;

import javax.swing.ImageIcon;

public class PictureServiceImpl implements PictureService {

    @Override
    public Image getFXPicture(PictureCategoryEnum pictureCategory, String name) {
        return null;
    }

    @Override
    public java.awt.Image getSwingPicture(PictureCategoryEnum pictureCategory, String name) {
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("pictures/" + pictureCategory.getPath() + name));
        return imageIcon.getImage();
    }
}
