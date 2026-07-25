/**
 * Utility for handling landing page themes, dark mode, dashboard tabs, and stat counters
 */
window.ThemeUtils = {
    /**
     * Applies an accent theme class to the document root while preserving dark mode.
     * @param {string} className - The CSS class name for the accent theme.
     */
    applyTheme: function(className) {
        const isDark = document.documentElement.classList.contains('dark');
        document.documentElement.classList.remove('theme-emerald', 'theme-amber', 'theme-rose');
        if (className) {
            document.documentElement.classList.add(className);
        }
        if (isDark) {
            document.documentElement.classList.add('dark');
        }
        localStorage.setItem('app-theme', className || '');
    },

    /**
     * Initializes the theme and dark mode state from LocalStorage on page load.
     */
    initTheme: function() {
        const savedTheme = localStorage.getItem('app-theme');
        const savedMode = localStorage.getItem('app-dark-mode');

        if (savedTheme) {
            document.documentElement.classList.add(savedTheme);
        }

        if (savedMode === 'dark' || (!savedMode && window.matchMedia('(prefers-color-scheme: dark)').matches)) {
            document.documentElement.classList.add('dark');
        } else {
            document.documentElement.classList.remove('dark');
        }
    },

    /**
     * Toggles dark mode on document element and persists selection.
     */
    toggleDarkMode: function() {
        const isDark = document.documentElement.classList.toggle('dark');
        localStorage.setItem('app-dark-mode', isDark ? 'dark' : 'light');
        return isDark;
    },

    /**
     * Switches the active tab in the Hero interactive dashboard showcase.
     * @param {string} tabName - The name of the tab panel (overview, metrics, security).
     */
    switchDashboardTab: function(tabName) {
        const panels = document.querySelectorAll('.dashboard-panel');
        const tabs = document.querySelectorAll('.dashboard-tab-btn');
        panels.forEach(p => p.classList.add('hidden'));
        tabs.forEach(t => t.classList.remove('active-tab'));

        const activePanel = document.getElementById('panel-' + tabName);
        const activeTab = document.getElementById('tab-' + tabName);
        if (activePanel) activePanel.classList.remove('hidden');
        if (activeTab) activeTab.classList.add('active-tab');
    },

    /**
     * Animates numeric values on stat cards.
     */
    animateCounters: function() {
        const counters = document.querySelectorAll('.stat-value');
        counters.forEach(counter => {
            const targetText = counter.getAttribute('data-target') || counter.innerText;
            if (!counter.getAttribute('data-target')) {
                counter.setAttribute('data-target', targetText);
            }
            const match = targetText.match(/([\d,.]+)/);
            if (!match) return;

            const numericString = match[1].replace(/,/g, '');
            const targetNum = parseFloat(numericString);
            if (isNaN(targetNum)) return;

            const prefix = targetText.substring(0, match.index);
            const suffix = targetText.substring(match.index + match[0].length);

            let start = 0;
            const duration = 1200;
            const startTime = performance.now();

            function updateCounter(currentTime) {
                const elapsed = currentTime - startTime;
                const progress = Math.min(elapsed / duration, 1);
                const currentVal = Math.floor(progress * targetNum);
                counter.innerText = prefix + currentVal.toLocaleString() + suffix;

                if (progress < 1) {
                    requestAnimationFrame(updateCounter);
                } else {
                    counter.innerText = targetText;
                }
            }
            requestAnimationFrame(updateCounter);
        });
    }
};
