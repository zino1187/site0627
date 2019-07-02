package net;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//�ֱܼ���� ���� �̿��� �����ϹǷ� �׷��ȸ��� ��������!!
public class GUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server; //���� û��� ��������!! ( ��ȭ�� �ƴ�)
	Thread thread;//����, ���, ����� ����ϱ� ����!!
						//��? ���ξ������ �߿��ϴϱ�...��ڴϱ�...
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("7777");
		bt = new JButton("��������");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		thread = new Thread() {
			//�����ڴ� ���������� �ڵ带 run�� �־�θ�
			//JVM �˾Ƽ� �������ش�..(�� runnable �� �о�־���)
			public void run() {
				startServer();
			}
		};
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);//�������� ���ʿ� ����
		add(scroll);
		//��ư�� �̺�Ʈ ���� 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//startServer();//�������� ����!!
				thread.start();
			}
		});
		
		setVisible(true);
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		
		try {
			server = new ServerSocket(port);//��������
			bt.setEnabled(false);//��ư ��Ȱ��ȭ
			area.append("���� ���� ����!!\n");
			area.append("Ŭ���̾�Ʈ ���� ��ٸ��� ��...\n");
			Socket client = server.accept(); //Ŭ���̾�Ʈ ���� û��!! (���, ����)
			//�ڹٴ� ������ ����̱⿡, �츮�� ����ζ�� �ҷ��� �� ������ü�� �ٷ�
			//main thread����.., ���� �������� ������ ���α׷��� ��ϴ� ���� 
			//Ư�� GUI ���α׷������� ������� �̺�Ʈ ����, �׷��� ������ ���...
			//���� ���� �����带 ���, ����, ���� ���� ��Ʈ����, ���α׷���ü�� ����
			//�ȵ���̵忡���� ������ ���� ����� ������...
			String ip = client.getInetAddress().getHostAddress();
			area.append(ip+"�� ����\n");
			
			//�����ڰ� �����Ǹ�, ��Ʈ���� �̾Ƽ� ��ȭ�� ��������!!!
			BufferedReader buffr = new BufferedReader(new InputStreamReader(client.getInputStream())); //��
			BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));//��
			
			while(true) {
				//��ȭ ����!!
				String msg=buffr.readLine();//���!!
				
				//������
				buffw.write(msg+"\n");
				buffw.flush();//���� ����
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
	
}







