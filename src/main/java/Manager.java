import business.service.PictureService;
import business.serviceimpl.PictureServiceImpl;
import enums.PictureCategoryEnum;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Manager {

    public Manager() {
        JFrame frame = new JFrame();
        frame.setSize(800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        PictureService pictureService = new PictureServiceImpl();

        JComponent c = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(pictureService.getSwingPicture(PictureCategoryEnum.MEDALS, "medal_1.jpg"), 0, 0, getWidth(), getHeight(), this);
            }
        };
        c.setSize(200, 200);
        panel.add(c);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Manager();
    }
}
