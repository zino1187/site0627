//���ڼ�����, Ŭ���̾�Ʈ�� �޼����� ���� ��, �״�� �����ϴ� ����
//�� ��Ʈ��ũ ������ �ǹ�!! ( ä�� ������ ����)
//�����̶�? ���꿡���� ���ϵ� ���ǿ����� ����ó�� �̹� ���������� 
//              ������� �κ��� �����س��� ����ü�� ���Ѵ�..
//				Ư�� ������ �κ��� �߻�ȭ ���ѳ��� �����ڰ� �޼���ȣ�⸸
//             ���ε� ��������� �����ϵ��� �س���..

//�ڹٳ�Ʈ��ũ������ ������ 2���� 
//  1) ��ȭ�� �޴� �뵵�� ����: ���������̶��� 
//  2) ��ȭ�� ���� : �����Ű� ���� 
package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server;
	int port=7777; //0~1023 ������ �ý��� ���� ��Ʈ�̹Ƿ� �����������
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);//���� ����!!
			System.out.println("������ ��ٸ��� ����!!");
			
			//Socket�� ��ȭ�� �������ִ� ��ȭ��� ����!!
			Socket client=server.accept();//������ ��ٸ�..���ξ������� ����δ�
							//�����ڰ� �߻��Ҷ����� �����¿� ����..����..
			System.out.println("������ �߰�!!");
			
			//Ŭ���̾�Ʈ�� ���� �޼��� ��������...�������� ���α׷�
			//���� �����Ͱ� ������ ���̹Ƿ�, �Է½�Ʈ���� �ʿ��ϴ�!!
			InputStream is = client.getInputStream();
			OutputStream os=client.getOutputStream();
			
			//���ڱ������ ���׷��̵�!!
			InputStreamReader input=new InputStreamReader(is);
			OutputStreamWriter output=new OutputStreamWriter(os);
			
			//���ڸ� ����, ���ڿ��� ���׷��̵� �Ϸ��� ���۽�Ʈ���� �ʿ���
			BufferedReader buffr=new BufferedReader(input);
			BufferedWriter buffw=new BufferedWriter(output);
			
			while(true) {
				String data=buffr.readLine();
				System.out.println(data);
				//�о���� �����͸� �ٽ� Ŭ���̾�Ʈ���� ������!!
				//==�������� ���α׷����� �����Ͱ� ������ ���̹Ƿ�
				//���!!
				buffw.write(data+"\n");
				buffw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}











