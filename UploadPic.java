/*基于tcp协议模拟多客户端并发的图片上传*/

import java.io.*;
import java.net.*;

//客户端
class Client{
	public static void main(String args[])throws Exception{
		Socket s = new Socket("127.0.0.1",10000);
		//客户端的数据目标
		OutputStream os = s.getOutputStream();
		//建立客户端的数据来源
		FileInputStream fis = new FileInputStream("1.bmp");
		byte[] b = new byte[1024];
		int length = 0;
		while((length=fis.read(b))!=-1){
			os.write(b,0,length);
		}
		//提示服务端数据已经写完，如果不提示，客户端的while循环无法正常结束，一直等待
		s.shutdownOutput();
		
		//另一个数据来源
		InputStream is = s.getInputStream();
		length = is.read(b);
		System.out.println(new String(b,0,length));
		
		fis.close();
		s.close();
	}
}

class UploadThread implements Runnable{
	private Socket s;
	UploadThread(Socket s){
		this.s = s;
	}
	public void run(){
		
		String ip = s.getInetAddress().getHostAddress();
		try{
			//服务端的数据来源
			InputStream is = s.getInputStream();
			//服务端的数据目标
			FileOutputStream fos = new FileOutputStream(ip+"server.bmp");
			byte[] b = new byte[1024];
			int length = 0;
			while((length = is.read(b))!=-1){
				fos.write(b,0,length);
			}
			OutputStream os = s.getOutputStream();
			os.write("上传成功！".getBytes());
			System.out.println(ip+"上传成功！");
			fos.close();
			s.close();
		}
		catch(Exception e){
			System.out.println(ip+"上传失败！");
		}
	}
}

//服务端
class Server{
	public static void main(String args[])throws Exception{
		ServerSocket ss = new ServerSocket(10000);
		
		while(true){
			Socket s = ss.accept();
			new Thread(new UploadThread(s)).start();
		}
		//ss.close();
	}
}
