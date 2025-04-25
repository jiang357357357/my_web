package com.personal.servlet;

import com.personal.model.TimelineEvent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/timeline")
public class TimelineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TimelineEvent> events = List.of(
//                new TimelineEvent("2023-01", "开始大学生活", "开始了我的计算机科学之旅。"),
//                new TimelineEvent("2024-06", "第一次实习", "担任初级开发工程师，参与项目开发。")
        );
        req.setAttribute("events", events);
        req.getRequestDispatcher("/timeline.jsp").forward(req, resp);
    }
}