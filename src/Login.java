import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;

public class Login {
	public static void main(String[] args){
		JFrame login = new JFrame("Login");
		JPanel panel = new JPanel();
		JTextField LoginName = new JTextField(20);
		JButton enter = new JButton("Login");
		
		panel.add(LoginName);
		panel.add(enter);
		login.setSize(300, 100);
		login.add(panel);
		login.setVisible(true);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		enter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ChatClient client = new ChatClient(LoginName.getText());
					login.setVisible(false);
					login.dispose();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		LoginName.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						ChatClient client = new ChatClient(LoginName.getText());
						login.setVisible(false);
						login.dispose();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
