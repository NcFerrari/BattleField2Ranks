package serviceimpl;

import javafx.scene.image.Image;
import service.PictureService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PictureServiceImpl implements PictureService {

    @Override
    public Image getFXPicture(String path) {
        return null;
    }

    @Override
    public java.awt.Image getSwingPicture(String path) {
        return null;
    }

    @Override
    public File getImage(String path) {
        return null;
    }

    public static void main(String[] args) {
    }

    private void saveIntoDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            InputStream inputStream = new FileInputStream("pictures/medals/medal_1.jpg");
            PreparedStatement ps = con.prepareStatement("INSERT INTO imgtable VALUES(?, ?, ?, ?)");
            ps.setString(1, "medal_1");
            ps.setBlob(2, inputStream);
            ps.execute();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void loadFromDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM imgtable");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String img = "img.png";
                FileOutputStream fos = new FileOutputStream(img);
                Blob blob = rs.getBlob("photo");
                int length = (int) blob.length();
                byte[] buf = blob.getBytes(1, length);
                fos.write(buf, 0, length);
                fos.close();
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
