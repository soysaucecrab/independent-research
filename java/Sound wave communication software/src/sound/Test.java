package sound;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test extends JFrame implements ActionListener{
	//필드 정의하기
	private JTextField inputMsg;
	
   //생성자
   public Test() {
      setSize(800, 500);
      setLocation(100, 100);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      //FlowLayout 객체 전달하기
      setLayout(new FlowLayout());
      
      //문자열을 입력할수 있는 UI 
      inputMsg=new JTextField(30);

      
      //전송버튼
      JButton sendBtn=new JButton("전송");
      sendBtn.setActionCommand("send");
      sendBtn.addActionListener(this);
      add(sendBtn);
      
      //삭제버튼
      JButton deleteBtn = new JButton("삭제");
      deleteBtn.setActionCommand("delete");
      deleteBtn.addActionListener(this);
      
      //패널 객체를 생성해서
      JPanel panel = new JPanel();
      //패널에 UI를 추가하고
      panel.add(inputMsg);
      panel.add(sendBtn);
      panel.add(deleteBtn);
      
      //패널 통채로 프레임에 추가하기
      add(panel,"Center");
      
      setVisible(true);
   }
   
   //run 했을때 실행순서가 시작되는 main 메소드 
   public static void main(String[] args) {
      new Test();
   }
   
   //ActionListener 인터페이스를 구현해서 강제로 Override 한 메소드 
   @Override
   public void actionPerformed(ActionEvent e) {
      //이벤트가 발생한 버튼에 설정된 action command 읽어오기 
      String command=e.getActionCommand();
      if(command.equals("send")) {
         //JTextField에 입력한 문자열 읽어오기
    	  String msg = inputMsg.getText();
    	  Cal cal = new Cal();
    	  int cl=3;
    	  if(msg.length()!=0) {
    		  cl = cal.calculate(msg);
    		  
    	  }
    	  else {
    		  JOptionPane.showMessageDialog(this, "write something");
		}
    	  if(cl==0) {
    		  JOptionPane.showMessageDialog(this, "It cannot be converted to ASCII code");
    		  clear();
    	  }
    	  if(cl==1) {
    		  JOptionPane.showMessageDialog(this, "sended");
    		  clear();
    	  }
      }else if (command.equals("delete")) {
    	  //빈 문자열을 넣어주어서 삭제하기
    	  clear();
      }
   }
   public void clear() {
	   inputMsg.setText("");
   }
}