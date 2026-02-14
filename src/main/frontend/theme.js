/**
 * Utility for handling landing page themes
 */
window.ThemeUtils = {
    /**
     * Applies a theme class to the document root and persists it.
     * @param {string} className - The CSS class name for the theme.
     */
    applyTheme: function(className) {
        // Remove existing theme classes if necessary (optional, since we replace the whole className)
        document.documentElement.className = className;
        localStorage.setItem('app-theme', className);
    },

    /**
     * Initializes the theme from LocalStorage on page load.
     */
    initTheme: function() {
        const savedTheme = localStorage.getItem('app-theme');
        if (savedTheme) {
            document.documentElement.className = savedTheme;
        }
    }
};
