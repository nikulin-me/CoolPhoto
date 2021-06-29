package nikulin.app.model.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView!=null){
            String args = request.getQueryString() != null ? request.getQueryString() : "";//args after? in uri
            String url = request.getRequestURI().toString() + "?" + args;
            response.setHeader("Turbolinks-Location",url);
        }
    }
}
