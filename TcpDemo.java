/*tcpЭ���µ����ӷ���*/
/*tcpЭ���£��ͻ��������˷���һ���ı����ڷ���˾�������󣬷����ı��Ĵ�д*/
import java.io.*;
import java.net.*;
//�ͻ���
class TcpClient{
	public static void main(String args[])throws Exception{
		//����Socket�࣬���������ӵ�ָ��ip��ָ���˿ں�
		Socket s = new Socket("127.0.0.1",10000);
		//��ȡs���������ͨ������������˷�����Ϣ
		OutputStream os = s.getOutputStream();
		InputStream is = s.getInputStream();
		byte[] b = new byte[1024];
		int length;
		String text = null;
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		while((text = bufr.readLine())!=null){
			//text = bufr.readLine();
			//����end��������
			if("end".equals(text)){
				break;
			}
			os.write(text.getBytes());
			//��ȡ��������ͨ����������ȡ����˵���Ϣ
			length = is.read(b);//������ֱ��������������д��
			System.out.println(new String(b,0,length));
		}
		
		//�ر�Socket
		s.close();//����������д��һ��-1
	}
}
//�����
class TcpServer{
	public static void main(String args[])throws Exception{
		//����ServerSocket��,��ָ��������˿�
		ServerSocket ss = new ServerSocket(10000);
		//��ȡ�ͻ���socket
		Socket s = ss.accept();//������ֱ�����յ��ͻ��˵�Socket����
		//�ӿͻ���socket�л�ȡip��data
		String ip = s.getInetAddress().getHostAddress();
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		byte[] b = new byte[1024];
		int length;
		while(true){
			length = is.read(b);//������ֱ���ͻ����������д��
			if(length==-1){//�ж�socket�Ƿ�ر�
				break;
			}
			String data = new String(b,0,length);
			System.out.println("ip::"+ip+" data::"+data);
			//ͨ��socket����������ͻ��˷�����Ϣ
			os.write(data.toUpperCase().getBytes());
		}
		
		//ͨ������˹رտͻ��˵�����
		//s.close();
		//ss.close();
		
	}
}