package com.sanfem.servlet;

import com.sanfem.dao.CodeDAO;
import com.sanfem.model.Code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代码集Servlet - 处理代码相关的HTTP请求
 */
@WebServlet("/code")
public class CodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CodeDAO codeDAO = new CodeDAO();

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
            case "detail":
                showDetail(request, response);
                break;
            case "add":
                request.getRequestDispatcher("/jsp/code/edit.jsp").forward(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCode(request, response);
                break;
            default:
                listCodes(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            updateCode(request, response);
        } else {
            saveCode(request, response);
        }
    }

    private void listCodes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Code> codes = codeDAO.findAll();
        request.setAttribute("codes", codes);
        request.getRequestDispatcher("/jsp/code/list.jsp").forward(request, response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Code code = codeDAO.findById(id);
        request.setAttribute("code", code);
        request.getRequestDispatcher("/jsp/code/detail.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Code code = codeDAO.findById(id);
        request.setAttribute("code", code);
        request.getRequestDispatcher("/jsp/code/edit.jsp").forward(request, response);
    }

    private void saveCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Code code = new Code();
        code.setTitle(request.getParameter("title"));
        code.setLanguage(request.getParameter("language"));
        code.setDescription(request.getParameter("description"));
        code.setContent(request.getParameter("content"));
        codeDAO.insert(code);
        response.sendRedirect(request.getContextPath() + "/code");
    }

    private void updateCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Code code = new Code();
        code.setId(Integer.parseInt(request.getParameter("id")));
        code.setTitle(request.getParameter("title"));
        code.setLanguage(request.getParameter("language"));
        code.setDescription(request.getParameter("description"));
        code.setContent(request.getParameter("content"));
        codeDAO.update(code);
        response.sendRedirect(request.getContextPath() + "/code");
    }

    private void deleteCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        codeDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/code");
    }
}
