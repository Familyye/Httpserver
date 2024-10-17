import com.sun.net.httpserver.HttpExchange; // 导入 HttpExchange 类，用于处理 HTTP 请求和响应
import com.sun.net.httpserver.HttpHandler; // 导入 HttpHandler 接口，用于定义 HTTP 请求处理器

import java.io.*; // 导入输入输出相关的类

public class ApiTestForwardHandler implements HttpHandler { // 实现 HttpHandler 接口

    @Override
    public void handle(HttpExchange httpExchange) throws IOException { // 重写 handle 方法以处理 HTTP 请求
        String response = "hello world"; // 定义响应内容
        httpExchange.sendResponseHeaders(200, 0); // 发送 HTTP 响应头，状态码200，内容长度为0

        InputStream is = httpExchange.getRequestBody(); // 获取请求体的输入流
        InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8"); // 创建输入流读取器，指定字符编码为UTF-8
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // 包装输入流读取器为缓冲读取器
        String str = bufferedReader.readLine(); // 读取请求体的第一行内容

        // 获取请求的URL（未使用，可以根据需要处理）
        String url = httpExchange.getRequestURI().toString(); // 获取请求的URI，并转换为字符串
//        byte[] bytes = new byte[is.available()]; // 预留代码，用于获取请求体的可用字节数
//        is.read(bytes); // 读取请求体的字节到数组中
//        String str = new String(bytes,"UTF-8"); // 将字节数组转换为字符串

        System.out.println("read post body " + str); // 打印读取到的请求体内容
        OutputStream os = httpExchange.getResponseBody(); // 获取响应体的输出流
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8"); // 创建输出流写入器，指定字符编码为UTF-8
        osw.write(str); // 将请求体内容写入响应体
        osw.flush(); // 确保所有数据都已写入输出流
        osw.close(); // 关闭输出流写入器

//        os.write(str.getBytes("UTF-8")); // 预留代码，用于将请求体字节写入响应体
//        os.close(); // 预留代码，关闭输出流
    }
}
