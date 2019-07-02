package chatting.memory;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatB extends JFrame{
	//�ʿ��ϸ� �����϶�!!
	JTextArea area;
	JScrollPane scroll;
	JTextField txt;
	JPanel p;
	ChatA chatA; //�ʿ��ϸ� ��������!!
	
	public ChatB(ChatA chatA) {
		area = new JTextArea();
		scroll = new JScrollPane(area);
		txt  = new JTextField(15);
		p = new JPanel();
		this.chatA=chatA;
		
		p.add(txt);
		//�����ڰ� ���̾ƿ��� �������� ������ ������ �ƴ϶�
		//����Ʈ ���̾ƿ��� �����...
		//�ڹ� GUI ��� ������Ʈ�� ���̾ƿ� �ɷ��� �ִ°� �ƴ϶�, 
		//�����̳����� ���̾ƿ� �ɷ��� �ִ�.. 
		//��� GUI ������Ʈ�� �з� 
		//   ���� ������ �� �ִ� ����  : �����̳� (Frame, Panel)
		//                                       ���� �����ϱ� ���� ��ġ�� ���...
		//                                     Frame : BorderLayout 
		//										Panel : FlowLayout
		//   ���ԵǴ� ����                 : ���־� ������Ʈ(Button, check...)
		add(scroll, BorderLayout.CENTER); //
		add(p, BorderLayout.SOUTH);
		
		//Ű���� �̺�Ʈ ���� 
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg=txt.getText();
					area.append(msg+"\n");
					chatA.area.append(msg+"\n");
					txt.setText("");
				}	
			}
		});	
		setVisible(true);
		setSize(300,400);
	}
}







