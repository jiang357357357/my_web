document.addEventListener("DOMContentLoaded", () => {
    const timelineToggleBtn = document.getElementById("timeline-toggle-btn");
    const timelineContainer = document.getElementById("timeline-container");
    const themeToggle = document.getElementById("theme-toggle");
    const themeOptions = document.getElementById("theme-options");
    const backToTop = document.getElementById("back-to-top");

    // 时间树切换
    let isTimelineOpen = false;
    window.toggleTimeline = () => {
        isTimelineOpen = !isTimelineOpen;
        timelineContainer.classList.toggle("active", isTimelineOpen);
        timelineToggleBtn.textContent = isTimelineOpen ? "收起" : "时间树";
    };

    // 时间树事件展开/收起
    const timelineEvents = document.querySelectorAll(".timeline-event");
    timelineEvents.forEach(event => {
        event.classList.add("collapsed"); // 初始收起
        event.addEventListener("click", (e) => {
            // 避免点击链接时触发展开/收起
            if (!e.target.classList.contains("event-link")) {
                event.classList.toggle("collapsed");
            }
        });
    });

    // 主题选择框显示/隐藏
    themeToggle.addEventListener("click", () => {
        themeOptions.style.display = themeOptions.style.display === "block" ? "none" : "block";
    });

    // 点击页面其他地方关闭主题选择框
    document.addEventListener("click", (e) => {
        if (!themeToggle.contains(e.target) && !themeOptions.contains(e.target)) {
            themeOptions.style.display = "none";
        }
    });

    // 主题切换
    window.changeTheme = (theme) => {
        if (theme === "dark") {
            document.body.classList.add("dark-mode");
        } else {
            document.body.classList.remove("dark-mode");
        }
        localStorage.setItem("theme", theme);
        themeOptions.style.display = "none";
    };

    // 加载保存的主题
    const savedTheme = localStorage.getItem("theme");
    if (savedTheme) {
        changeTheme(savedTheme);
    }

    window.submitContact = () => {
        const name = document.getElementById("contact-name").value;
        const email = document.getElementById("contact-email").value;
        const message = document.getElementById("contact-message").value;

        console.log('表单数据：', { name, email, message });

        if (name && email && message) {
            const url = '/my_web_war_exploded/sendEmail'; // 明确URL
            console.log('发送POST请求到：', window.location.origin + url);
            fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, email, message })
            })
                .then(response => {
                    console.log('响应状态：', response.status, response.statusText);
                    // 调试：打印原始响应
                    response.clone().text().then(text => console.log('原始响应内容：', text));
                    if (!response.ok) throw new Error(`HTTP错误：${response.status}`);
                    return response.json();
                })
                .then(data => {
                    console.log('解析后的JSON：', data);
                    if (data.success) {
                        alert('留言已发送到邮箱！');
                        document.getElementById("contact-name").value = "";
                        document.getElementById("contact-email").value = "";
                        document.getElementById("contact-message").value = "";
                    } else {
                        alert('发送失败：' + data.message);
                    }
                })
                .catch(error => {
                    console.error('请求失败：', error);
                    alert('发送出错啦！' + error);
                });
        } else {
            console.warn('表单校验失败：请填写所有字段');
            alert("请填写所有字段！");
        }
    };

    // 回到顶部按钮显示/隐藏
    window.addEventListener("scroll", () => {
        backToTop.classList.toggle("visible", window.scrollY > 300);
    });

    // 回到顶部功能
    window.scrollToTop = () => {
        window.scrollTo({ top: 0, behavior: "smooth" });
    };
});