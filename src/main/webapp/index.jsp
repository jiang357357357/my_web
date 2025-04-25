<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>我的个人网站</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/theme.css">
    <link rel="stylesheet" href="css/timeline.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <script src="js/timeline.js"></script>
    <script src="js/stars.js"></script>
</head>
<body>
<div class="stars-container" id="stars-container"></div>
<div class="book-page">
    <header>
        <h1>店长的个人网站</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/home">首页</a> |
            <div class="theme-selector">
                <a href="javascript:void(0)" id="theme-toggle">主题</a>
                <div class="theme-options" id="theme-options">
                    <button onclick="changeTheme('light')">浅色模式</button>
                    <button onclick="changeTheme('dark')">深色模式</button>
                </div>
            </div>
        </nav>
    </header>
    <main>
        <section class="info-section">
            <h2>关于我</h2>
            <p>我是店长，20岁，我既是一名硬件爱好者，也是一名java开发者，喜欢哲学和书籍，崇尚简约生活。</p>
            <p>邮箱：2740954024@qq.com | 电话：13098925086</p>
        </section>
        <section class="info-section">
            <h2>我目前的项目</h2>
            <ul>
                <li><strong>个人网站系统</strong> - 使用jsp开发自己博客平台，当前项目。</li>
                <li><strong>live2d项目</strong> - 改写官方源码，整合为网页可以使用的形式。</li>
                <li><strong>esp32互联手套</strong> - 基于esp32和websocket开发的项目。</li>
            </ul>
        </section>
        <section class="info-section">
            <h2>联系我</h2>
            <div class="contact-form">
                <p><label>姓名：<input type="text" id="contact-name" placeholder="请输入您的姓名"></label></p>
                <p><label>邮箱：<input type="email" id="contact-email" placeholder="请输入您的邮箱"></label></p>
                <p><label>留言：<textarea id="contact-message" placeholder="请输入您的留言"></textarea></label></p>
                <button onclick="submitContact()">提交</button>
            </div>
        </section>
    </main>

    <!-- 每日一言 -->
    <div class="daily-quote">
        <p>每日一言：<span id="daily-quote-text">${dailyQuote}</span></p>
    </div>

    <!-- 页脚 -->
    <footer>
        <div class="footer-links">
            <a href="https://github.com/jiang357357357" target="_blank" title="GitHub">
                <i class="fab fa-github"></i>
            </a> |
            <a href="https://space.bilibili.com/312989098" target="_blank" title="B站">
                <i class="fab fa-bilibili"></i>
            </a> |
            <a href="https://blog.csdn.net/zz357357357?type=blog" target="_blank" title="博客">
                <i class="fas fa-blog"></i>
            </a>
        </div>
        <div class="footer-icp">
            <p>© 2025 林哲 | <a href="https://beian.miit.gov.cn/" target="_blank">鲁ICP备2025xxxx号</a></p>
        </div>
    </footer>

    <!-- 时间树 -->
    <div class="timeline-container" id="timeline-container">
        <div class="timeline">
            <h2>时间树</h2>
            <c:choose>
                <c:when test="${empty events}">
                    <p>暂无时间树事件</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="event" items="${events}">
                        <div class="timeline-event">
                            <h3>${event.date}<span></span></h3>
                            <h4>${event.title}</h4>
                            <p class="description">${event.description}</p>
                            <c:if test="${not empty event.url}">
                                <a href="${event.url}" class="event-link" target="_blank">查看详情</a>
                            </c:if>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- 时间树按钮 -->
    <button id="timeline-toggle-btn" onclick="toggleTimeline()">时间树</button>

    <!-- 回到顶部按钮 -->
    <button id="back-to-top" onclick="scrollToTop()">↑ 回到顶部</button>
</div>
</body>
</html>