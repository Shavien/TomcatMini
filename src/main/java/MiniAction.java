import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Shavien on 2016/10/27.^_^
 */
public class MiniAction implements Servlet{
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("MiniAction init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Service running...");
        servletResponse.getWriter().println("<h1>This message is come from Vien Mini Tomcat</h1>");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
