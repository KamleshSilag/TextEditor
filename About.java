import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class About extends JFrame{
	JLabel editorname,developername,version;
	
	About()
	{
		setSize(300,250);
		setLayout(null);
		setTitle("About");
		setLocation(450,200);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
	
		editorname = new JLabel("             MY EDITOR");
		editorname.setFont(new Font("Arial",Font.BOLD,20));
		editorname.setBackground(Color.white);
		editorname.setOpaque(true);
		editorname.setBounds(0,50,300,20);
		
		version = new JLabel("Ver 1.1");
		version.setBackground(Color.RED);
		version.setOpaque(true);
		version.setBounds(100,70,40,20);
		
		developername = new JLabel("-Kamlesh Silag");
		developername.setBackground(Color.white);
		developername.setOpaque(true);
		developername.setBounds(140,140,100,20);
		
		add(editorname);
		add(version);
		add(developername);
		
	}

}
