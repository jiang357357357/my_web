package com.personal.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

@WebServlet("/sendEmail")
public class EmailServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(EmailServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        JsonObject responseJson = new JsonObject();

        try {
            LOGGER.info("收到发送邮件请求");

            // 读取JSON
            BufferedReader reader = req.getReader();
            StringBuilder jsonInput = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) jsonInput.append(line);
            LOGGER.info("原始JSON输入: " + jsonInput);

            Gson gson = new Gson();
            JsonObject data = gson.fromJson(jsonInput.toString(), JsonObject.class);
            if (data == null) throw new IllegalArgumentException("JSON解析失败：输入为空");

            String name = data.get("name").getAsString();
            String email = data.get("email").getAsString();
            String message = data.get("message").getAsString();

            LOGGER.info("表单数据 - 姓名: " + name + ", 邮箱: " + email + ", 留言: " + message);

            // 发送邮件
            sendEmail(name, email, message);

            LOGGER.info("邮件发送成功");
            responseJson.addProperty("success", true);
        } catch (Exception e) {
            LOGGER.severe("错误: " + e.getMessage() + ", 堆栈: " + getStackTrace(e));
            responseJson.addProperty("success", false);
            responseJson.addProperty("message", e.getMessage());
        } finally {
            resp.getWriter().write(responseJson.toString());
        }
    }

    private void sendEmail(String name, String email, String message) throws MessagingException {
        String host = "smtp.qq.com";
        String port = "465";
        String username = "2740954024@qq.com"; // 替换成你的QQ邮箱
        String password = "rulutfakzbzkdfjc"; // 替换成授权码

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        LOGGER.info("邮件配置 - 主机: " + host + ", 端口: " + port + ", 用户: " + username);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(username));
        msg.setSubject("新留言 from " + name);
        msg.setText("姓名: " + name + "\n邮箱: " + email + "\n留言: " + message);

        LOGGER.info("邮件内容 - 主题: " + msg.getSubject() + ", 收件人: " + username);
        Transport.send(msg);
    }

    // 辅助方法：获取异常堆栈
    private String getStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}