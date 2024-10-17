import java.net.InetAddress; // 导入 InetAddress 类，用于表示 IP 地址
import java.net.NetworkInterface; // 导入 NetworkInterface 类，用于获取网络接口信息
import java.net.SocketException; // 导入 SocketException 类，用于处理网络相关异常
import java.util.Enumeration; // 导入 Enumeration 类，用于遍历集合

public class LocalIPPrinter {

    // 打印本机所有有效的 IP 地址及指定端口
    public static void printLocalIPs(int port) {
        try {
            System.out.println("本机的所有有效 IP 地址及端口:"); // 输出提示信息
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); // 获取所有网络接口
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement(); // 获取当前网络接口

                // 过滤掉未启用的接口、回环接口和虚拟接口
                if (!networkInterface.isUp() || networkInterface.isLoopback() || networkInterface.isVirtual()) {
                    continue; // 如果接口不符合条件，则跳过
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses(); // 获取当前网络接口的所有 IP 地址
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement(); // 获取当前 IP 地址
                    if (inetAddress instanceof java.net.Inet4Address) { // 只打印 IPv4 地址
                        // 输出有效的服务器地址及端口
                        System.out.println("Server地址: http://" + inetAddress.getHostAddress() + ":" + port + "/");
                    }
                }
            }
        } catch (SocketException e) {
            // 捕获 SocketException 异常并输出错误信息
            System.err.println("无法获取本机 IP 地址: " + e.getMessage());
        }
    }
}
