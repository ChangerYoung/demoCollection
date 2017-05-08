import java.net.*;

class UdpSend{
	public static void main(String args[]) throws Exception {
		//����һ��socket�����ڷ���udp��Ϣ,��Ϊ��ָ���˿ں�
		DatagramSocket ds = new DatagramSocket();
		byte[] b = "this is a message from port".getBytes();
		DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName("127.0.0.1"),10000);
		ds.send(dp);
		//�м�Ҫ�ڽ�����ɺ�ر���Դ
		ds.close();
	}
}

class UdpReceive{
	public static void main(String args[]) throws Exception {
		//����һ��socket�����ڽ���udp��Ϣ����Ϊ��ָ���˿ں�
		DatagramSocket ds = new DatagramSocket(10000);
		byte b[] = new byte[1024];
		DatagramPacket dp = new DatagramPacket(b,b.length);
		//��һ��whileѭ����ʼ�ս�����Ϣ
		while(true){
			ds.receive(dp);
			String ip = dp.getAddress().getHostAddress();
			String data = new String(dp.getData(),0,dp.getLength());
			int port = dp.getPort();
			System.out.println("ip:"+ip+" data:"+data+" port:"+port);
		}
		
		//�ر���Դ
		//ds.close();
		
	}
}