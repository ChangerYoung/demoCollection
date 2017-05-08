/*
多线程的udp协议dos命令行的聊天程序
*/

//发送消息的类
import java.net.*;
import java.io.*;

class Send implements Runnable{
	private DatagramSocket ds;
	public Send(DatagramSocket ds){
		this.ds = ds;
	}
	public void run(){
		try{
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while((line = bufr.readLine())!=null){
				if("886".equals(line)){
					break;
				}
				byte[] b = line.getBytes();
				DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName("127.0.0.1"),10002);
				ds.send(dp);
				
			}
		}
		catch(Exception e){
			System.out.println("发送失败！");
		}
	}
}

//接受消息的类
class Receive implements Runnable{
	private DatagramSocket ds;
	public Receive(DatagramSocket ds){
		this.ds = ds;
	}
	public void run(){
		try{
			while(true){
				byte b[] = new byte[1024];
				DatagramPacket dp = new DatagramPacket(b,b.length);
				ds.receive(dp);
				
				String ip = dp.getAddress().getHostAddress();
				String data = new String(dp.getData(),0,dp.getLength());
				System.out.println(ip+"::"+data);
			}
		}
		catch(Exception e){
			System.out.println("接受失败！");
		}
	}
}

public class ChartDemo{
	public static void main(String args[]) throws Exception{
		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket receiveSocket = new DatagramSocket(10002);
		
		new Thread(new Send(sendSocket)).start();
		new Thread(new Receive(receiveSocket)).start();
	}
}

