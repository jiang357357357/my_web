document.addEventListener("DOMContentLoaded", () => {
    const starsContainer = document.getElementById("stars-container");

    function createStars() {
        // 清除现有星星
        starsContainer.innerHTML = "";

        // 生成200个星星
        for (let i = 0; i < 200; i++) {
            const star = document.createElement("div");
            star.style.position = "absolute";
            star.style.background = "#ffffff";
            star.style.borderRadius = "50%";

            // 随机大小（1px到3px）
            const size = Math.random() * 2 + 1;
            star.style.width = `${size}px`;
            star.style.height = `${size}px`;

            // 随机位置
            star.style.left = `${Math.random() * 100}%`;
            star.style.top = `${Math.random() * 100}%`;

            // 随机明暗（透明度0.3到1）
            star.style.opacity = Math.random() * 0.7 + 0.3;

            // 随机闪烁动画
            star.style.animation = `twinkle ${Math.random() * 2 + 1}s infinite alternate`;

            starsContainer.appendChild(star);
        }
    }

    // 闪烁动画
    const styleSheet = document.createElement("style");
    styleSheet.textContent = `
        @keyframes twinkle {
            from { opacity: 0.3; }
            to { opacity: 1; }
        }
    `;
    document.head.appendChild(styleSheet);

    // 主题切换时重新生成星星
    const observer = new MutationObserver(() => {
        if (document.body.classList.contains("dark-mode")) {
            createStars();
        }
    });
    observer.observe(document.body, { attributes: true, attributeFilter: ["class"] });

    // 初始检查
    if (document.body.classList.contains("dark-mode")) {
        createStars();
    }
});