package ictgradschool.project.control;

import ictgradschool.project.model.User;
import ictgradschool.project.model.UserDAO;
import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.ImageCompressUtil;
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
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

// This servlet users provided code from https://gitlab.com/auckland-ict-grad-school/lab-exercises/web/web-lab-11 to process file

@WebServlet(name = "avatar-upload", urlPatterns = "/upload-avatar")
public class AvatarUploadServlet extends HttpServlet {
    private File uploadsFolder;
    private File tempFolder;
    private final String imagesRelativePath = "/images/avatar";
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
        boolean newUser = (request.getSession().getAttribute("existingUser") == null);
        User user = (User) request.getSession().getAttribute("newUser");

        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String targetFileName = "customer-avatar" + user.getUserID();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            File targetImageFile;

            for (FileItem fi : fileItems) {
                if (!fi.isFormField() && acceptableMimeTypes.contains(fi.getContentType())) {
                    String fileType = fi.getContentType().split("/")[1];
                    targetImageFile = new File(uploadsFolder, targetFileName + "." + fileType);
                    if (fileType.equals("jpg") || fileType.equals("jpeg")) {
                        ImageCompressUtil.compressJpgImage(fi, targetImageFile);
                    } else
                        fi.write(targetImageFile);

                    try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                        user = UserDAO.setUserAvatarPath(connection, user.getUserID(), targetFileName + "." + fileType);
                    }

                    if (newUser) {
                        request.getSession().setAttribute("newUser", user);
                        request.getRequestDispatcher("WEB-INF/view/user-blog-setting.jsp").forward(request, response);
                    } else {
                        request.getSession().setAttribute("loggedUser", user);
                        response.sendRedirect("./user-profile?user-id=" + user.getUserID());
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
