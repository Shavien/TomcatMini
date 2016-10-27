import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by Shavien on 2016/10/27.^_^
 */
public class ServletProcessor {
    private Request request;
    private Response response;

    public ServletProcessor(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void doResponse() {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;
            File classpath = new File(Constants.CLASSES_PATH);
            String repository = new URL("file", null, classpath.getCanonicalPath() + File.separator).toString();
            System.out.println(repository+servletName);
            urls[0] = new URL(null, repository, urlStreamHandler);//指定上下文用指定处理程序对spec解析创建URL
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
