/*����tcpЭ��ģ���ͻ��˲�����ͼƬ�ϴ�*/

import java.io.*;
import java.net.*;

//�ͻ���
class Client{
	public static void main(String args[])throws Exception{
		Socket s = new Socket("127.0.0.1",10000);
		//�ͻ��˵�����Ŀ��
		OutputStream os = s.getOutputStream();
		//�����ͻ��˵�������Դ
		FileInputStream fis = new FileInputStream("1.bmp");
		byte[] b = new byte[1024];
		int length = 0;
		while((length=fis.read(b))!=-1){
			os.write(b,0,length);
		}
		//��ʾ����������Ѿ�д�꣬�������ʾ���ͻ��˵�whileѭ���޷�����������һֱ�ȴ�
		s.shutdownOutput();
		
		//��һ��������Դ
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
			//����˵�������Դ
			InputStream is = s.getInputStream();
			//����˵�����Ŀ��
			FileOutputStream fos = new FileOutputStream(ip+"server.bmp");
			byte[] b = new byte[1024];
			int length = 0;
			while((length = is.read(b))!=-1){
				fos.write(b,0,length);
			}
			OutputStream os = s.getOutputStream();
			os.write("�ϴ��ɹ���".getBytes());
			System.out.println(ip+"�ϴ��ɹ���");
			fos.close();
			s.close();
		}
		catch(Exception e){
			System.out.println(ip+"�ϴ�ʧ�ܣ�");
		}
	}
}

//�����
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
