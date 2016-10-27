import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Shavien on 2016/10/27.^_^
 */
public class TomcatServer {

    public static void main(String[] args) {

    }

    private void startServer() {
        int port = 80;
        int backlog = 1;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, backlog, InetAddress.getByName("127.0.0.1"));
            //三个参数：服务器监听端口号，客户连接请求队列长度，服务器要绑定的IP。
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try{
                socket = serverSocket.accept();//从FIFO队列取一个socket对象
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                Request request = new Request(inputStream);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
