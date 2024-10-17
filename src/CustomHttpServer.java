import com.sun.net.httpserver.HttpHandler; // 导入 HttpHandler 接口
import com.sun.net.httpserver.HttpServer; // 导入 HttpServer 类

import java.io.IOException; // 导入 IOException 类
import java.net.InetSocketAddress; // 导入 InetSocketAddress 类

public class CustomHttpServer {

    private HttpServer mServer; // 声明 HttpServer 实例变量

    // 构造函数，接受端口号并创建 HTTP 服务器
    public CustomHttpServer(int port) {
        try {
            // 创建一个新的 HttpServer 实例，绑定到指定端口
            mServer = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            // 捕获并打印异常信息
            System.err.println("无法创建 HTTP 服务器: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 设置请求路径和处理器的映射关系
    public void setHandler(String path, HttpHandler handler) {
        // 为指定的路径创建上下文，并指定处理器
        mServer.createContext(path, handler);
    }

    // 启动 HTTP 服务器以开始接收请求
    public void startForward() {
        mServer.start(); // 启动服务器
        System.out.println("HTTP 服务器已启动"); // 打印服务器启动信息
    }
}
