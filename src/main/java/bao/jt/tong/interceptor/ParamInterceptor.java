package bao.jt.tong.interceptor;

import bao.jt.tong.domain.Controller;
import bao.jt.tong.domain.Controllers;
import bao.jt.tong.util.XMLUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

//定义一个参数为空筛选的拦截器
public class ParamInterceptor implements HandlerInterceptor {

    String filepath = this.getClass().getClassLoader().getResource("ParamInterceptor.xml").getPath();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean result = true;
        String[] urls = request.getRequestURL().toString().split("/");
        String controllerName = urls[urls.length - 1];
        Controllers cs = (Controllers) XMLUtil.convertXmlFileToObject(Controllers.class, filepath);
        List<Controller> cso = cs.getController();
        for (Controller s : cso) {
            if (controllerName.equals(s.getControllerName())) {
                if (s.getParam().size() != request.getParameterMap().keySet().size()) {
                    result = false;
                    break;
                } else{
                    for(String ps:s.getParam()){
                        if("".equals(request.getParameter(ps))){
                            result = false;
                            break;
                        }
                    }
                }
            }
        }
        if (!result) {
            PrintWriter out = response.getWriter();
            out.print("Missing parameters");
        }
        //System.out.println(cs);
        //Controllers{Controller=[Controller{ControllerName='Login', Param=[username, password]}, Controller{ControllerName='Register', Param=[username, password, name, phone]}]}
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex)
            throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
