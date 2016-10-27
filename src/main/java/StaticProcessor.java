import java.io.*;

/**
 * Created by Shavien on 2016/10/27.^_^
 */
public class StaticProcessor {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream outputStream;
    private Request request;

    public StaticProcessor(OutputStream outputStream, Request request) {
        this.outputStream = outputStream;
        this.request = request;
    }

    public void doResponse() throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(Constants.WEB_ROOT_PATH,request.getUri());
            fis = new FileInputStream(file);
            int ch = fis.read(buffer, 0, BUFFER_SIZE);
            while(ch!=-1){
                outputStream.write(buffer, 0, ch);
                ch = fis.read(buffer, 0, BUFFER_SIZE);
            }
        } catch (FileNotFoundException e) {
            ResponseError error = new ResponseError(outputStream,404,"File Not Found", Constants.ERROR_PAGE_404);
            error.getErrorPage();
            System.out.println(e);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
