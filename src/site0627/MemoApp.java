package site0627;
import  java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoApp extends JFrame{
	//상수 사용목적: 의미를 부여하여 직관성을 높이려고..
	public static final int MY_SIZE = 18; 
	JMenuBar bar; 
	JMenu[] menu;
	JMenuItem[] item;
	String[] menuTitle= {"파일","편집","서식","보기","도움말"};
	String[] itemTitle= {"새로만들기","열기","저장","다른이름으로 저장","페이지설정","인쇄","끝내기"};
	JTextArea area;
	JScrollPane scroll;//스크롤
	 
	JFileChooser chooser;//파일 탐색기
	
	//FileInputStream은 바이트 기반 스트림이므로, 1바이트씩 이해한다..
	// 따라서 한글과 관련된 즉 영미권 영어가 아닌 경우는 깨져서 보인다..
	// 2바이씩 묶어서 이해할 수 있는 스트림인 문자기반 스트림으로 해결함
	//문자 기반 스트림은 주로 ~Reader(입력), ~Writer(출력)
	FileInputStream fis; //파일을 대상으로 한 입력스트림
									//실행중인 자바 프로그램으로 데이터를 읽어들인다
	FileReader reader;
	//버퍼기반 스트림 :  데이터 단위를 한줄씩 모아서, 다 모아지면 그때 한번
	//입력을 일으키는 효율성있는 스트림이다!!
	//API 에 거의 접두어가 Buffered~ 
	BufferedReader buffr;
	
	public MemoApp() {
		bar = new JMenuBar();
		menu = new JMenu[menuTitle.length]; //메뉴를-+ 올려놓을 공간만 확보 
		item = new JMenuItem[itemTitle.length];
		
		for(int i=0;i<menuTitle.length;i++) {
			menu[i] = new JMenu(menuTitle[i]);			
			bar.add(menu[i]);//바에 메뉴들 부착!!			
		}	
		
		for(int i=0;i<itemTitle.length;i++) {
			item[i] = new JMenuItem(itemTitle[i]);
			if(i==4 || i==6)menu[0].addSeparator();
			menu[0].add(item[i]); //메뉴에 아이템들 부착!!
			
			//이벤트 구현 
			item[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//누가 눌렸는지 즉 이벤트 소스가 누구인지 알아내기
					JMenuItem obj=(JMenuItem)e.getSource();
					if(obj.getText().equals("열기") ) {
						//area.append("you selected open item??\n");	
						openFile();
					}
				}
			});
		}
		
		area= new JTextArea();
		scroll = new JScrollPane(area);
		//생성한다고 하여 탐색기 창이 열리는 것이 아님...창 여는 메서드 따로잇음
		chooser = new JFileChooser("D:/final_workspace");
		
		area.setFont(new Font("verdana", Font.BOLD, MemoApp.MY_SIZE));
		//생성된 객체들을 조합한다!! ( 배치하자!!)
		setJMenuBar(bar);//언제나 상단에 부착됨
		add(scroll); //개발자가 레이아웃을 지정하지 않으면 , 
		//프레임의 BorderLayout
		
		//윈도우 이벤트 구현 
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//열려있는 DB or 스트림 무조건 닫아야 함..
				//메모리 누수...메모리꽉차서 다운..
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(buffr !=null) {
					try {
						buffr.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);//프로세스 종료
			}
		});
		
		setSize(800,650);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/*파일 열기 */
	public void openFile() {
		int result=chooser.showOpenDialog(this);
		//1,0 으로 개발을 하면 직관적이지 못하므로, 상수를 이용한다..
		if(result == JFileChooser.APPROVE_OPTION) {
			//실행중인 프로그램에서 파일을 읽어들이자!!!
			File file=chooser.getSelectedFile();
			String path=file.getAbsolutePath();
		
			try {
				reader = new FileReader(path);
				buffr = new BufferedReader(reader);
				
				//생성된 스트림 관을 이용하여 바이트를 읽어들이자!!
				String data=null;
				while(true){
					data=buffr.readLine();
					if(data==null)break;
					System.out.println(data);
					area.append(data+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		new MemoApp();
	}

}








