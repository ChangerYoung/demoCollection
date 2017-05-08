import java.net.*;

class UdpSend{
	public static void main(String args[]) throws Exception {
		//创建一个socket，用于发送udp消息,并为其指定端口号
		DatagramSocket ds = new DatagramSocket();
		byte[] b = "this is a message from port".getBytes();
		DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName("127.0.0.1"),10000);
		ds.send(dp);
		//切记要在接受完成后关闭资源
		ds.close();
	}
}

class UdpReceive{
	public static void main(String args[]) throws Exception {
		//创建一个socket，用于接受udp消息，并为其指定端口号
		DatagramSocket ds = new DatagramSocket(10000);
		byte b[] = new byte[1024];
		DatagramPacket dp = new DatagramPacket(b,b.length);
		//用一个while循环来始终接受消息
		while(true){
			ds.receive(dp);
			String ip = dp.getAddress().getHostAddress();
			String data = new String(dp.getData(),0,dp.getLength());
			int port = dp.getPort();
			System.out.println("ip:"+ip+" data:"+data+" port:"+port);
		}
		
		//关闭资源
		//ds.close();
		
	}
}