import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Shavien on 2016/10/27.^_^
 */
public class ResponseError {
    private int errorCode;
    private String errorMsg;
    private String errorHtml;
    private OutputStream outputStream;

    public ResponseError(OutputStream outputStream,int errorCode,String errorMsg,String html){
        this.outputStream = outputStream;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorHtml = html;
    }

    public void getErrorPage(){
        String responseMsg = "HTTP/1.1 "+errorCode+" "+errorMsg+"\r\n"
                            +"Content-Type:text/html\r\n"
                            +"Content-Length:23\r\n"
                            +"\r\n"
                            +errorHtml;
        try {
            System.out.println(responseMsg);
            outputStream.write(responseMsg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
