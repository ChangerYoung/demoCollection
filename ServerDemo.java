/*自定义服务端，在浏览器端口访问
  浏览器web端采用http协议，而http协议是基于tcp的，所以在浏览器发送请求的时候
  同样会在客户端产生一个Socket，并将数据封装进去，在服务端接受并处理
  
  在浏览器中输入ip和端口号即可访问
  http://127.0.0.1:10000/
*/
import java.io.*;
import java.net.*;

class Server{
	public static void main(String[] args)throws Exception{
		ServerSocket ss = new ServerSocket(10000);
		Socket s = ss.accept();
		
		System.out.println(s.getInetAddress().getHostAddress()+"...connected");
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
		
		pw.println("welcome to visit!");
		
		s.close();
		ss.close();
	}
}