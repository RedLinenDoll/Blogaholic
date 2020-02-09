package ictgradschool.project.util;

import org.apache.commons.fileupload.FileItem;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/* This class is used to compress avatar jpg/jpeg files, as we only display avatars in icon size, and
* compressing the files save the space in our server.
* The code is based on
* https://www.tutorialspoint.com/java_dip/image_compression_technique.htm
* */
public class ImageCompressUtil {
    public static void compressJpgImage(FileItem originalImageFileItem, File compressedImageFile) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalImageFileItem.getInputStream());

        OutputStream outputStream =new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();

        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        writer.setOutput(imageOutputStream);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.05f);
        writer.write(null, new IIOImage(originalImage, null, null), param);

        outputStream.close();
        imageOutputStream.close();
        writer.dispose();
    }
}
