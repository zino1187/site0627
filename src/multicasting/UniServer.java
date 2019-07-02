package multicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//�ֱܼ���� ���� �̿��� �����ϹǷ� �׷��ȸ��� ��������!!
public class UniServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server; // ���� û��� ��������!! ( ��ȭ�� �ƴ�)
	Thread thread;// ����, ���, ����� ����ϱ� ����!!
					// ��? ���ξ������ �߿��ϴϱ�...��ڴϱ�...
	Vector<ServerThread> vec;
	
	public UniServer() {
		p_north = new JPanel();
		t_port = new JTextField("7777");
		bt = new JButton("��������");
		area = new JTextArea();
		scroll = new JScrollPane(area);

		thread = new Thread() {
			// �����ڴ� ���������� �ڵ带 run�� �־�θ�
			// JVM �˾Ƽ� �������ش�..(�� runnable �� �о�־���)
			public void run() {
				startServer();
			}
		};
		vec = new Vector<ServerThread>();
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);// �������� ���ʿ� ����
		add(scroll);
		// ��ư�� �̺�Ʈ ����
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// startServer();//�������� ����!!
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
			server = new ServerSocket(port);// ��������
			bt.setEnabled(false);// ��ư ��Ȱ��ȭ
			area.append("���� ���� ����!!\n");
			area.append("Ŭ���̾�Ʈ ���� ��ٸ��� ��...\n");
			while (true) {
				Socket client = server.accept();
				String ip = client.getInetAddress().getHostAddress();
				area.append(ip + "�� ����\n");
				
				//�����ڰ� �߻������Ƿ�, �����带 �����Ͽ� ��ȭ�� ������ ������
				ServerThread st = new ServerThread(client , this);
				st.start();//��ȭ ����!!!
				
				//������ ��ܿ� ������ ���!!
				vec.add(st);
				
				area.append("���� ������ ���� "+vec.size()+"\n");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UniServer();
	}

}
