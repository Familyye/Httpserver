import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // 创建并启动自定义 HTTP 服务器，监听 20801 端口
        CustomHttpServer httpServer = new CustomHttpServer(20801);

        // 设置处理器，将 "/" 路径的请求转发给 ApiTestForwardHandler 处理
        httpServer.setHandler("/", new ApiTestForwardHandler());

        // 打印本地网络接口的 IP 地址，确认服务器的可访问性
        LocalIPPrinter.printLocalIPs(20801);

        // 启动服务器的请求转发功能
        httpServer.startForward();

        // 打印服务器启动成功的消息
        System.out.println("server启动成功");

    }
}
