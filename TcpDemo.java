/*tcp协议下的连接服务*/
/*tcp协议下，客户端向服务端发送一串文本，在服务端经过处理后，返回文本的大写*/
import java.io.*;
import java.net.*;
//客户端
class TcpClient{
	public static void main(String args[])throws Exception{
		//创建Socket类，并将其连接到指定ip的指定端口号
		Socket s = new Socket("127.0.0.1",10000);
		//获取s的输出流，通过输出流向服务端发送消息
		OutputStream os = s.getOutputStream();
		InputStream is = s.getInputStream();
		byte[] b = new byte[1024];
		int length;
		String text = null;
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		while((text = bufr.readLine())!=null){
			//text = bufr.readLine();
			//输入end结束程序
			if("end".equals(text)){
				break;
			}
			os.write(text.getBytes());
			//获取输入流，通过输入流读取服务端的消息
			length = is.read(b);//阻塞，直到服务端向输出流写入
			System.out.println(new String(b,0,length));
		}
		
		//关闭Socket
		s.close();//会在输入流写入一个-1
	}
}
//服务端
class TcpServer{
	public static void main(String args[])throws Exception{
		//创建ServerSocket类,并指定其监听端口
		ServerSocket ss = new ServerSocket(10000);
		//获取客户端socket
		Socket s = ss.accept();//阻塞，直到接收到客户端的Socket连接
		//从客户端socket中获取ip和data
		String ip = s.getInetAddress().getHostAddress();
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		byte[] b = new byte[1024];
		int length;
		while(true){
			length = is.read(b);//阻塞，直到客户端向输出流写入
			if(length==-1){//判断socket是否关闭
				break;
			}
			String data = new String(b,0,length);
			System.out.println("ip::"+ip+" data::"+data);
			//通过socket的输出流给客户端发送信息
			os.write(data.toUpperCase().getBytes());
		}
		
		//通过服务端关闭客户端的连接
		//s.close();
		//ss.close();
		
	}
}