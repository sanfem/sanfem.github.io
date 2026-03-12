package com.sanfem.servlet;

import com.sanfem.dao.DiaryDAO;
import com.sanfem.model.Diary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 日记Servlet - 处理日记相关的HTTP请求
 */
@WebServlet("/diary")
public class DiaryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DiaryDAO diaryDAO = new DiaryDAO();

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
                request.getRequestDispatcher("/jsp/diary/edit.jsp").forward(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteDiary(request, response);
                break;
            default:
                listDiaries(request, response);
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
            updateDiary(request, response);
        } else {
            saveDiary(request, response);
        }
    }

    private void listDiaries(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Diary> diaries = diaryDAO.findAll();
        request.setAttribute("diaries", diaries);
        request.getRequestDispatcher("/jsp/diary/list.jsp").forward(request, response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = parseId(request.getParameter("id"));
        if (id < 0) {
            response.sendRedirect(request.getContextPath() + "/diary");
            return;
        }
        Diary diary = diaryDAO.findById(id);
        request.setAttribute("diary", diary);
        request.getRequestDispatcher("/jsp/diary/detail.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = parseId(request.getParameter("id"));
        if (id < 0) {
            response.sendRedirect(request.getContextPath() + "/diary");
            return;
        }
        Diary diary = diaryDAO.findById(id);
        request.setAttribute("diary", diary);
        request.getRequestDispatcher("/jsp/diary/edit.jsp").forward(request, response);
    }

    private void saveDiary(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Diary diary = new Diary();
        diary.setTitle(request.getParameter("title"));
        diary.setContent(request.getParameter("content"));
        diary.setMood(request.getParameter("mood"));
        diary.setWeather(request.getParameter("weather"));
        diaryDAO.insert(diary);
        response.sendRedirect(request.getContextPath() + "/diary");
    }

    private void updateDiary(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = parseId(request.getParameter("id"));
        if (id < 0) {
            response.sendRedirect(request.getContextPath() + "/diary");
            return;
        }
        Diary diary = new Diary();
        diary.setId(id);
        diary.setTitle(request.getParameter("title"));
        diary.setContent(request.getParameter("content"));
        diary.setMood(request.getParameter("mood"));
        diary.setWeather(request.getParameter("weather"));
        diaryDAO.update(diary);
        response.sendRedirect(request.getContextPath() + "/diary");
    }

    private void deleteDiary(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = parseId(request.getParameter("id"));
        if (id < 0) {
            response.sendRedirect(request.getContextPath() + "/diary");
            return;
        }
        diaryDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/diary");
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
}
