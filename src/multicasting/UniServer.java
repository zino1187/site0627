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

//콘솔기반의 서버 이용이 불편하므로 그래픽모드로 개발하자!!
public class UniServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server; // 접속 청취용 서버소켓!! ( 대화용 아님)
	Thread thread;// 지연, 대기, 루프등에 사용하기 위함!!
					// 왜? 메인쓰레드는 중요하니깐...운영자니깐...
	Vector<ServerThread> vec;
	
	public UniServer() {
		p_north = new JPanel();
		t_port = new JTextField("7777");
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);

		thread = new Thread() {
			// 개발자는 독립수행할 코드를 run에 넣어두면
			// JVM 알아서 실행해준다..(단 runnable 에 밀어넣야함)
			public void run() {
				startServer();
			}
		};
		vec = new Vector<ServerThread>();
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);// 프레임의 북쪽에 부착
		add(scroll);
		// 버튼에 이벤트 구현
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// startServer();//서버가동 시작!!
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
			server = new ServerSocket(port);// 서버생성
			bt.setEnabled(false);// 버튼 비활성화
			area.append("서버 가동 시작!!\n");
			area.append("클라이언트 접속 기다리는 중...\n");
			while (true) {
				Socket client = server.accept();
				String ip = client.getInetAddress().getHostAddress();
				area.append(ip + "님 접속\n");
				
				//접속자가 발생했으므로, 쓰레드를 생성하여 대화를 나누게 해주자
				ServerThread st = new ServerThread(client , this);
				st.start();//대화 시작!!!
				
				//접속자 명단에 쓰레드 담기!!
				vec.add(st);
				
				area.append("현재 접속자 수는 "+vec.size()+"\n");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UniServer();
	}

}
