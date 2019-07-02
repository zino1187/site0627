package net;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame{
	//필요하면 보유하라!!
	JTextArea area;
	JScrollPane scroll;
	JTextField txt;
	Choice ch; //ip선택
	JTextField t_port; //포트입력
	JButton bt_connect;
	JPanel p_north, p;
	String[] ip= {"67","51","55","15","60","143","79","41"};
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public ChatClient() {
		area = new JTextArea();
		scroll = new JScrollPane(area);
		txt  = new JTextField(15);
		p = new JPanel();
		
		ch=new Choice();
		t_port = new JTextField("7777");
		bt_connect = new JButton("접속");
		p_north = new JPanel();
		//아이피 설정 
		for(int i=0;i<ip.length;i++) {
			ch.add("192.168.0."+ip[i]);
		}
		
		//패널에 부착
		p_north.add(ch);
		p_north.add(t_port);
		p_north.add(bt_connect);
		//패널을 북쪽영역에 부착 
		add(p_north, BorderLayout.NORTH);
		
		p.add(txt);
		add(scroll, BorderLayout.CENTER); //
		add(p, BorderLayout.SOUTH);
		
	
		//키보드 이벤트 구현 
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() ==KeyEvent.VK_ENTER) {
					send(txt.getText());
				}
			}
		});
		
		//접속 버튼 이벤트 구현 
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {
		String ip=ch.getSelectedItem();
		int port=Integer.parseInt(t_port.getText()); // "7777" --> 7777
		
		//서버에 접속!!
		try {
			client = new Socket(ip , port);
			
			//소켓으로부터 스트림 얻기!!!
			buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//서버측으로부터 받기!!
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
		//서버측에 메세지 전송!!
		try {
			buffw.write(msg+"\n");//문자열의 끝에 줄바꿈표시가 있어야 한줄의 끝을
			//이해한다!! 따라서 서버측에서는 문자열의 끝이 없기 때문에 계속
			//대기하고 있음..
			buffw.flush();//버퍼처리된 출력스트림 계열에서 이 메서드를 호출하면
			//스트림에 현재 쌓여있는 데이터를 모두 방출!!
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}
}







