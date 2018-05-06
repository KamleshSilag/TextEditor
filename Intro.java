import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.text.BadLocationException;

public class Intro {
	JButton jb;
	JLabel lb,background;

	Intro()
	{
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.black);
		
		lb = new JLabel("             MY EDITOR");
		lb.setBounds(0,50,300,30);
		lb.setBackground(Color.WHITE);
		//lb.setContentAreaFilled(false);
		lb.setOpaque(true);
		lb.setFont(new Font("Arial", Font.BOLD, 20));
		
		

		jb = new JButton("OPEN");
		jb.setBackground(Color.RED);
		jb.setContentAreaFilled(false);
		jb.setFont(new Font("Arial",Font.BOLD,15));
        jb.setOpaque(true);

		jb.setBounds(90,170,100,20);
		
		  
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				f.dispose();
				try {
					Main m =new Main();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		f.setVisible(true);
		f.setTitle("Welcome");
		f.setSize(300,300);
		f.setLocation(450,100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setResizable(false);
		
		f.add(jb);
		f.add(lb);
	
		
	}
	
   
	public static void main(String args[])
	{
		Intro i = new Intro();

	}
}
