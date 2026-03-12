package com.sanfem.servlet;

import com.sanfem.dao.PhotoDAO;
import com.sanfem.model.Photo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 相册Servlet - 处理照片相关的HTTP请求
 */
@WebServlet("/photo")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,      // 1 MB
    maxFileSize = 1024 * 1024 * 10,        // 10 MB
    maxRequestSize = 1024 * 1024 * 50      // 50 MB
)
public class PhotoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final PhotoDAO photoDAO = new PhotoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "upload":
                request.getRequestDispatcher("/jsp/photo/upload.jsp").forward(request, response);
                break;
            case "delete":
                deletePhoto(request, response);
                break;
            default:
                listPhotos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        uploadPhoto(request, response);
    }

    private void listPhotos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Photo> photos = photoDAO.findAll();
        request.setAttribute("photos", photos);
        request.getRequestDispatcher("/jsp/photo/list.jsp").forward(request, response);
    }

    private void uploadPhoto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part filePart = request.getPart("photo");

        if (filePart != null && filePart.getSize() > 0) {
            String originalName = getFileName(filePart);
            String extension = "";
            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }
            String fileName = UUID.randomUUID().toString() + extension;

            String uploadPath = getServletContext().getRealPath("/uploads/photos");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            filePart.write(uploadPath + File.separator + fileName);

            Photo photo = new Photo();
            photo.setTitle(title);
            photo.setDescription(description);
            photo.setFilePath("uploads/photos/" + fileName);
            photoDAO.insert(photo);
        }

        response.sendRedirect(request.getContextPath() + "/photo");
    }

    private void deletePhoto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = parseId(request.getParameter("id"));
        if (id < 0) {
            response.sendRedirect(request.getContextPath() + "/photo");
            return;
        }
        Photo photo = photoDAO.findById(id);
        if (photo != null) {
            String filePath = getServletContext().getRealPath("/" + photo.getFilePath());
            File file = new File(filePath);
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.err.println("Failed to delete file: " + filePath);
                }
            }
            photoDAO.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/photo");
    }

    private int parseId(String param) {
        if (param == null || param.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
