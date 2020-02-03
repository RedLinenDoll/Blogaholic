package ictgradschool.project.control;

import ictgradschool.project.util.AutoIncrementIDGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "article-image-upload", urlPatterns = "/article-image-upload")
public class ArticleImageUpload extends HttpServlet {
    private File uploadsFolder;
    private File tempFolder;
    private final String imagesRelativePath = "/images/article-images";
    private final List<String> acceptableMimeTypes = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    @Override
    public void init() throws ServletException {
        super.init();

        // Get the upload folder, ensure it exists.
        this.uploadsFolder = new File(getServletContext().getRealPath(imagesRelativePath));
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }

        // Create the temporary folder that the file-upload mechanism needs.
        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currID = AutoIncrementIDGenerator.getID();
        System.out.println("reached here!");
        String targetFileName = "article-image-" + currID;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullSizeImageFile;

            for (FileItem fi : fileItems) {
                if (!fi.isFormField() && acceptableMimeTypes.contains(fi.getContentType())) {
                    String fileType = fi.getContentType().split("/")[1];
                    fullSizeImageFile = new File(uploadsFolder, targetFileName + "." + fileType);
                    String fileName = targetFileName + "." + fileType;
                    String imageLocation = request.getContextPath() + "/images/article-images/" + fileName;
                    fi.write(fullSizeImageFile);
                    String json = "{\"location\":\"" + imageLocation + "\"}";
                    response.getWriter().print(json);
                    return;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
