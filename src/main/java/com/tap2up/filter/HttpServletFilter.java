package com.tap2up.filter;

import com.alibaba.fastjson.JSONObject;
import com.tap2up.utils.EncryptUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/09/12 16:39
 * Description:
 * Version: V1.0
 */
@WebFilter(urlPatterns = "/alf/*")//如果这里写@Compnent就是拦截所有请求
public class HttpServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
        String data = request.getParameter("data");
        String key = request.getParameter("key");
        String str = null;
        try {
            str = EncryptUtils.Decrypt(data,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(str);
        Set set = jsonObject.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str2 = it.next();  //参数名
            m.put(str2.toLowerCase(),new String[]{jsonObject.getString(str2)});
        }
        ParameterServletRequestWrapper parameterRequestWrapper = new ParameterServletRequestWrapper((HttpServletRequest) request, m);
        //继续向后传递修改后的request,拦截器不能实现。
        chain.doFilter(parameterRequestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
