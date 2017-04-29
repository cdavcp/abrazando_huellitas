package sigep.service;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Stateless
public class FileService{

    private static final String PATH_RUBROS = "/rubros/";

    public void saveImg(File f, String name){
        try {

            ByteArrayInputStream baisData = new ByteArrayInputStream(Files.readAllBytes(f.toPath()));
            BufferedImage imag= ImageIO.read(baisData);
            File outputfile = new File(name + ".jpg");
            ImageIO.write(imag, "jpg", outputfile);
            System.out.println("Image file written successfully");

        } catch (IOException e) {
            System.out.println("Error guardando imagen");
        }
    }

    public File getImgByName(String name){
        return new File(name + ".jpg");
    }


}
