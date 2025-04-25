package com.personal.servlet;

import com.personal.model.TimelineEvent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    // 心灵代码·智慧箴言库
    private static final String[] QUOTES = {
            // 编程哲思
            "生活就像编程，调试的过程总是比写代码更长，却让作品趋于完美。",
            "每一天都是新的代码行，用热情作注释，用坚持写逻辑，编译出精彩人生。",
            "不要害怕报错，每个红色警告都是成长发出的邀请函。",

            // 诗意代码
            "代码如诗，在逻辑的平仄中寻找创意的韵脚。",
            "指尖敲击的是键盘，心中流淌的是星河。",
            "在二进制的世界里，我们书写着改变世界的十四行诗。",

            // 励志箴言
            "世界很大，代码很小，但每一行都可能成为未来的支点。",
            "Keep coding, keep dreaming, keep shining! 你的代码终将成为别人的光。",
            "人生就像一个Bug，不是无解，只是还没找到正确的断点。",

            // 生活智慧
            "一杯咖啡氤氲清晨，三行代码勾勒世界。",
            "编译等待时抬头看云，调试困顿时静听雨声。",
            "程序员是现代的炼金术士，将咖啡因转化为改变世界的能量。",

            // 哲理思考
            "最优雅的算法往往源自最简单的观察。",
            "代码会过时，但解决问题的智慧永远闪光。",
            "在无限循环中寻找突破，这就是生命的隐喻。",

            // 幽默代码
            "爱情就像段错误，总是在最意想不到的时候发生。",
            "我的代码和盆栽一样，都需要定期维护才不会枯萎。",
            "程序员最浪漫的告白：'我愿为你写一辈子的单元测试。'",

            // 中英双语
            "Code is poetry. // 代码即诗篇",
            "Debug the world. // 调试整个世界",
            "Hello World -> Hello Better World // 从问好世界到改变世界",

            // 星辰大海
            "我们写的不是代码，而是驶向未来的星舰。",
            "每个程序员都是银河系的建筑师，用逻辑搭建数字文明。",
            "当你在终端输入命令，其实是在向宇宙发送信号。"
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 随机选择一句每日一言
        Random random = new Random();
        String dailyQuote = QUOTES[random.nextInt(QUOTES.length)];
        req.setAttribute("dailyQuote", dailyQuote);

        // 创建时间树事件列表
        List<TimelineEvent> events = new ArrayList<>();

        // 添加事件
        events.add(new TimelineEvent("2023-09", "进入大学", "开始了我的专业学习，认识了许多志同道合的友人。", null));
        events.add(new TimelineEvent("2023-10", "以python作为第一门编程语言", "虽然这花了我四个月的时间来适应", ""));
        events.add(new TimelineEvent("2023-11", "靠编程赚到第一桶金", "虽然酬金只有十五块，但收获了宝贵的经验。", null));
        events.add(new TimelineEvent("2023-12", "开始学习c语言", "这是另一种可能得可能", null));
        events.add(new TimelineEvent("2024-01", "使用pygame重构植物大战僵尸的框架", "基本功能已经实现，只不过画面有点抽象。", null));
        events.add(new TimelineEvent("2024-04", "开始学习51单片机", "这个世界于我而言的另一种可能。", null));
        events.add(new TimelineEvent("2024-07", "使用javafx制作了可视化的图书馆操作系统", "虽然功能比较简单，但这是我第一次独自写完一个完整的项目", null));
        events.add(new TimelineEvent("2024-09", "独自完成了蓝牙遥控小车", "每个模块都是由我独自进行代码编写。", "https://github.com/linzhe/graduation-project"));
        events.add(new TimelineEvent("2024-11", "完成基于stm32的四足机器人的开发，同时学习3d打印", "向前迈进了一大步", null));
        events.add(new TimelineEvent("2024-12", "开始着手ai桌宠的开发", "全新的想法和现有的技术整合，demo版本于1.18号完成", null));
        events.add(new TimelineEvent("2025-01", "完成ai桌宠的开发", "虽然还有很多不足，不过后面重构起来倒是方便", null));
        events.add(new TimelineEvent("2025-02", "开始学习esp32和互联网", "我将开始实现我的想法", null));
        events.add(new TimelineEvent("2025-03", "整合硬件与ai，启动基于esp32为客户端与主端链接的项目", "一个宏大的愿景，这将花费我一整个学期的时间", null));
        events.add(new TimelineEvent("2025-03", "部署个人网站", "算是esp32项目的副产物", null));
        events.add(new TimelineEvent("2025-04", "加入协会，开始进行项目方面的合作", "虽然还有很多不足，不过后面重构起来倒是方便", null));
        // 按时间倒序排序
        events.sort((e1, e2) -> e2.getDate().compareTo(e1.getDate()));

        // 调试日志
        System.out.println("Events size: " + events.size());
        for (TimelineEvent event : events) {
            System.out.println("Event: " + event.getDate() + " | " + event.getTitle() + " | URL: " + event.getUrl());
        }

        // 设置请求属性
        req.setAttribute("events", events);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}