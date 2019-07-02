package chatting.memory;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatA extends JFrame{
	//필요하면 보유하라!!
	JTextArea area;
	JScrollPane scroll;
	JTextField txt;
	JButton bt;
	JPanel p;
	ChatB chatB;
	
	public ChatA() {
		area = new JTextArea();
		scroll = new JScrollPane(area);
		txt  = new JTextField(15);
		bt = new JButton("오픈");
		p = new JPanel();
		
		p.add(txt);
		p.add(bt);
		//개발자가 레이아웃을 지정하지 않으면 에러가 아니라
		//디폴트 레이아웃이 적용됨...
		//자바 GUI 모든 컴포넌트가 레이아웃 능력이 있는게 아니라, 
		//컨테이너형만 레이아웃 능력이 있다.. 
		//모든 GUI 컴포넌트의 분류 
		//   남을 포함할 수 있는 유형  : 컨테이너 (Frame, Panel)
		//                                       남을 포함하기 위해 배치를 고민...
		//                                     Frame : BorderLayout 
		//										Panel : FlowLayout
		//   포함되는 유형                 : 비주얼 컴포넌트(Button, check...)
		add(scroll, BorderLayout.CENTER); //
		add(p, BorderLayout.SOUTH);
		
		//상대방 띄우기!!
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openB();
			}
		});
		
		//키보드 이벤트 구현 
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() ==KeyEvent.VK_ENTER) {
					//나와 너의 창에 문자열 뿌리기!!
					String msg=txt.getText();//입력한 값!!!
					area.append(msg+"\n");
					chatB.area.append(msg+"\n");
					//입력 데이터 초기화 
					txt.setText("");
				}
			}
		});
		
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void openB() {
		//대화 상대방인 ChatB를 생성한다!!
		//call by reference : 객체자료형
		//call by value : 기본자료형
		chatB = new ChatB(this);
	}
	
	public static void main(String[] args) {
		new ChatA();
	}
}







