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

//콘솔기반의 서버 이용이 불편하므로 그래픽모드로 개발하자!!
public class GUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server; //접속 청취용 서버소켓!! ( 대화용 아님)
	Thread thread;//지연, 대기, 루프등에 사용하기 위함!!
						//왜? 메인쓰레드는 중요하니깐...운영자니깐...
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("7777");
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		thread = new Thread() {
			//개발자는 독립수행할 코드를 run에 넣어두면
			//JVM 알아서 실행해준다..(단 runnable 에 밀어넣야함)
			public void run() {
				startServer();
			}
		};
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);//프레임의 북쪽에 부착
		add(scroll);
		//버튼에 이벤트 구현 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//startServer();//서버가동 시작!!
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
			server = new ServerSocket(port);//서버생성
			bt.setEnabled(false);//버튼 비활성화
			area.append("서버 가동 시작!!\n");
			area.append("클라이언트 접속 기다리는 중...\n");
			Socket client = server.accept(); //클라이언트 접속 청취!! (대기, 지연)
			//자바는 쓰레드 기반이기에, 우리가 실행부라고 불렀던 그 실행주체가 바로
			//main thread엿다.., 메인 쓰레드의 역할은 프로그램을 운영하는 역할 
			//특히 GUI 프로그램에서는 사용자의 이벤트 감지, 그래픽 랜더링 등등...
			//따라서 메인 쓰레드를 대기, 루프, 지연 상태 빠트리면, 프로그램자체가 불통
			//안드로이드에서는 컴파일 에러 대상일 정도다...
			String ip = client.getInetAddress().getHostAddress();
			area.append(ip+"님 접속\n");
			
			//접속자가 감지되면, 스트림을 뽑아서 대화를 진행하자!!!
			BufferedReader buffr = new BufferedReader(new InputStreamReader(client.getInputStream())); //귀
			BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));//입
			
			while(true) {
				//대화 시작!!
				String msg=buffr.readLine();//듣기!!
				
				//보내기
				buffw.write(msg+"\n");
				buffw.flush();//버퍼 비우기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
	
}







