package com.finup.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huangrongchao on 2018/4/24.
 * @version 1.0
 */
public class RewriteFilter implements Filter {
    /**
     * 需要rewrite到的目的地址
     */
    public static final String REWRITE_TO = "rewriteUrl";
    /**
     * 拦截的url,url通配符之前用英文分号隔开
     */
    public static final String REWRITE_PATTERNS = "urlPatterns";
    private Set<String> urlPatterns = null;//配置url通配符
    private String rewriteTo = null;

    @Override
    public void init(FilterConfig cfg) throws ServletException {
        //初始化拦截配置
        rewriteTo = cfg.getInitParameter(REWRITE_TO);
        String exceptUrlString = cfg.getInitParameter(REWRITE_PATTERNS);
        if (StringUtils.isNotEmpty(exceptUrlString)) {
            urlPatterns = Collections.unmodifiableSet(
                    new HashSet<>(Arrays.asList(exceptUrlString.split(";", 0))));
        } else {
            urlPatterns = Collections.emptySet();
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String servletPath = request.getServletPath();
        String context = request.getContextPath();
        //匹配的路径重写
        if (isMatches(urlPatterns, servletPath)) {
            req.getRequestDispatcher(context + "/" + rewriteTo).forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 匹配返回true，不匹配返回false
     *
     * @param patterns 正则表达式或通配符
     * @param url      请求的url
     * @return
     */
    private boolean isMatches(Set<String> patterns, String url) {
        if (null == patterns) {
            return false;
        }
        for (String str : patterns) {
            if (str.endsWith("/*")) {
                String name = str.substring(0, str.length() - 2);
                if (url.startsWith("/static"))
                    return false;
                if (url.contains(name)) {
                    return true;
                }
            } else {
                Pattern pattern = Pattern.compile(str);
                if (pattern.matcher(url).matches()) {
                    return true;
                }
            }
        }
        return false;
    }
}
