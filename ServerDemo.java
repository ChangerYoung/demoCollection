/*�Զ������ˣ���������˿ڷ���
  �����web�˲���httpЭ�飬��httpЭ���ǻ���tcp�ģ���������������������ʱ��
  ͬ�����ڿͻ��˲���һ��Socket���������ݷ�װ��ȥ���ڷ���˽��ܲ�����
  
  �������������ip�Ͷ˿ںż��ɷ���
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