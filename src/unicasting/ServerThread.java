package unicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JTextArea;

/*������ ����, ���� ���������� ��ȭ�� �����ؾ� �ϹǷ�,
 * �ϳ��� �������� ���������� ����� ���ν�������� 
 * ������� ��������!!
 * �����ڰ� 10���̸� 10���� �����尡 �ʿ��ϹǷ�, .java
 * �� �����Ѵ�!!
 * */
public class ServerThread extends Thread{
										/*  is  a  */
	Socket client;
	//������ ���Ӱ� �����ϸ� �ȵǰ�, ������ ����û��� �߻���
	//�ٷ� �� ������ �Ѱܹ���!!
	BufferedReader buffr;
	BufferedWriter buffw;
	JTextArea area; //null
	Vector vec;
	String ip;
	boolean flag=true; //������ ����ְ�...
	
	public ServerThread(Socket client, UniServer uniServer) {
		this.client = client;
		this.area = uniServer.area; //���ο� area�� �ƴ϶�, ������ ���� �� area!!!!
		this.vec = uniServer.vec;
		
		ip = client.getInetAddress().getHostAddress();
		ip = ip.substring(ip.lastIndexOf(".")+1 , ip.length());
		
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream())); // ��
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));// ��
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//��ȭ ó�� �ڵ�!!! ( ���, ���ϴ°�) , �Է� buffr
	public String listen() {
		String msg=null;
		try {
			msg=buffr.readLine();
			area.append(ip+" ���� ��: "+msg+"\n");
		} catch (IOException e) {
			//e.printStackTrace();
			flag=false;
			vec.remove(this);//���� ����!!
			area.append(ip+"���� �������ϴ�, ���� ���� ����"+vec.size()+"\n");
		}
		return msg;
	}
	
	//���� �޼����� �Ű������� ����!!
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�����ڴ� ���� ������ �ڵ带 run�� ������ �ؾ��ϰ�, run�� �ݴ� �극�̽���
	//������ ������� �����ֱ� �� dead ���°� �ȴ�..���� ���� �ʱ� ���� 
	//���ѷ����� ó������!!
	public void run() {
		while(flag) {
			String msg=listen(); //���
			if(flag) {
				send(msg);//���ϱ�!!
			}
		}
	}
}

















