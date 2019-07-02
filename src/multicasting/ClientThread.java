package multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

//û��� ����ڰ� ��� �׼��� ������ �ʴ���, ���ѷ����� �����ؾ� ��
//�Ƿ�, ���� ���� ������� û��������� ���״ٰ���, ���α׷��� �����..
//������ �����尡 �ʿ��ϴ�!!!
public class ClientThread extends Thread{
	ChatClient chatClient;
	Socket client;
	BufferedReader buffr; //����
	BufferedWriter buffw;//���ϱ��
	JTextArea area;
	boolean flag=true;
	
	public ClientThread(ChatClient chatClient) {
		this.chatClient=chatClient;
		//�������κ��� ��Ʈ�� ���!!!
		client = chatClient.client;
		area = chatClient.area;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//���� ���� ���� �ʾƵ� ������ ���� ��� û�밡 �����Ϸ���
	//while ������ ó���ؾ� ��..
	//���������κ��� �ޱ�!!
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine();
			area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String msg) {
		//�������� �޼��� ����!!
		try {
			buffw.write(msg+"\n");//���ڿ��� ���� �ٹٲ�ǥ�ð� �־�� ������ ����
			//�����Ѵ�!! ���� ������������ ���ڿ��� ���� ���� ������ ���
			//����ϰ� ����..
			buffw.flush();//����ó���� ��½�Ʈ�� �迭���� �� �޼��带 ȣ���ϸ�
			//��Ʈ���� ���� �׿��ִ� �����͸� ��� ����!!
			//listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	//���������� �ڵ带 �ۼ��Ѵ�!!
	public void run() {
		while(flag) {
			listen();
		}
	}
}









