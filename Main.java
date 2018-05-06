import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;
public class Main extends JFrame implements ActionListener{
	
	JTextArea jtx ;
	JMenuBar jmenubar;
	JMenu file,edit,help;
	JMenuItem newfile,open,savefile,exit,cut,copy,paste,about;
	String selectedtext;
	
	JLabel fontsize;
	String fonts[]={"12","14","16","18","20","22","24","26","28","30","32","34","36"};
	
	JLabel styles;
	String style[]={"Notepad","Dracula","CodeBlue","Sixteen"};
	
	JLabel fontfamilytext;
	String fontfamilys[]={"Arial","Consolas","Elephant"};
	
	JComboBox jcm;
	JComboBox jcmstyles;
	JComboBox fontsfamily;

	
	public void removeHighlights(JTextComponent textComp) {
        	System.out.println("removed");
		   Highlighter hilite = textComp.getHighlighter();
		          
		   Highlighter.Highlight[] hilites = hilite.getHighlights();

		    for (int i = 0; i < hilites.length; i++) {
		    	if (hilites[i].getPainter() instanceof MyHighlightPainter) {
		              hilite.removeHighlight(hilites[i]);
		      }
		   }
		}
	
	public void highLight(JTextComponent textComp, String[] pattern) {   
		  
		     removeHighlights(textComp);
		     Highlighter.HighlightPainter myHighlighter = new MyHighlightPainter(Color.RED);

		  try {
		              
		     Highlighter hilite = textComp.getHighlighter();                                
		     Document doc = textComp.getDocument();      
		     String text = doc.getText(0, doc.getLength());         
		     for (int i = 0; i < pattern.length; i++) {                          
		        int pos = 0;
		        
		        
		         while ((pos = text.indexOf(pattern[i], pos)) >= 0) 
		         {
		           hilite.addHighlight(pos, pos + pattern[i].length(),myHighlighter); 
		           pos += pattern[i].length();
		         }
		     }
		     
		    } 
		  catch (BadLocationException e) {
			  System.out.print("Exception");
			  }
		          
		}
	
	Main() throws BadLocationException
	{
		String [] javaWords = {"do", "import", "public", "throws", "boolean", "double", "instanceof", "return", "transient", "break", "else", " int ", "short","try", "byte", "extends", "interface", "static", "void", "case", "final", "long", "volatile", "catch", "finally", "native", "super", "while", "char", "float","new", "switch", "class", "for", "package", "synchronized", "continue", "if", "private","this", "default", "implements", "protected", "const", "goto", "null", "true", "false"};
		
		setSize(500,500);
		setTitle("ATOM");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 100);
		setResizable(false);
		setLayout(null);
		
		jtx = new JTextArea("//Write your program here");
		jtx.setFont(new Font("Arial",Font.BOLD,18));
		JScrollPane sp = new JScrollPane(jtx);
		sp.setBounds(5,50,475,400);
		add(sp);
	
		
		
			
		jtx.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke)
			{
				highLight(jtx,javaWords);
				System.out.println("key pressed");
			}
		});

		
		jmenubar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		
		newfile = new JMenuItem("New");
		newfile.setActionCommand("New");
		
		open = new JMenuItem("Open");
		open.setActionCommand("Open");
		
		savefile = new JMenuItem("Save");
		savefile.setActionCommand("Save");
		
		exit = new JMenuItem("Exit");
		exit.setActionCommand("Exit");
		
		cut = new JMenuItem("Cut");
		cut.setActionCommand("Cut");
		
		copy = new JMenuItem("Copy");
		copy.setActionCommand("Copy");
		
		paste = new JMenuItem("Paste");
		paste.setActionCommand("Paste");
		
		about = new JMenuItem("About");
		about.setActionCommand("About");
			
		newfile.addActionListener(this);
		open.addActionListener(this);
		savefile.addActionListener(this);
		exit.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		about.addActionListener(this);
		
		
		file.add(newfile);
		file.add(open);
		file.add(savefile);
		file.add(exit);
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		
		help.add(about);
		
		
		jmenubar.add(file);
		jmenubar.add(edit);
		jmenubar.add(help);
		
		setJMenuBar(jmenubar);
		
		//Adding fonts components
		fontsize = new JLabel("FontSize");
		fontsize.setBounds(400,0,50,20);
		add(fontsize);
		
		jcm = new JComboBox<>(fonts);
		jcm.setBounds(400,20,50,20);
		add(jcm);
		
		
		jcm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String data = jcm.getItemAt(jcm.getSelectedIndex())+"";
				int fonts = Integer.parseInt(data);
				jtx.setFont(new Font(fontsfamily.getItemAt(fontsfamily.getSelectedIndex())+"",Font.BOLD,fonts));
				
			}
		});
		
		styles = new JLabel("Select Style");
		styles.setBounds(300,0,100,20);
		add(styles);
		
		jcmstyles = new JComboBox(style);
		jcmstyles.setBounds(300,20,80,20);
		add(jcmstyles);
		
		jcmstyles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedStyle = jcmstyles.getItemAt(jcmstyles.getSelectedIndex())+"";
				System.out.println("selected style :"+selectedStyle);
				if(selectedStyle.equals("Notepad"))
				{
					jtx.setBackground(Color.white);
					jtx.setForeground(Color.black);
					jtx.setCaretColor(Color.black);
				}
				else if(selectedStyle.equals("Dracula"))
				{
					jtx.setBackground(Color.black);
					jtx.setForeground(Color.WHITE);
					jtx.setCaretColor(Color.blue);  // for cursor color
				}
				else if(selectedStyle.equals("CodeBlue"))
				{
					jtx.setBackground(Color.blue);
					jtx.setForeground(Color.WHITE);
					jtx.setCaretColor(Color.black);  // for cursor color
				}
				else if(selectedStyle.equals("Sixteen"))
				{
					jtx.setBackground(Color.darkGray);
					jtx.setForeground(Color.BLUE);
					jtx.setCaretColor(Color.green);
				}
				
				
			}
		});
		
		
		fontfamilytext= new JLabel("Font");
		fontfamilytext.setBounds(250,0,70,20);
		add(fontfamilytext);
		
		fontsfamily= new JComboBox<>(fontfamilys);
		fontsfamily.setBounds(210,20,70,20);
		add(fontsfamily);
		
		fontsfamily.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fontselected = fontsfamily.getItemAt(fontsfamily.getSelectedIndex())+"";
				String data = jcm.getItemAt(jcm.getSelectedIndex())+"";
				int fonts = Integer.parseInt(data);
				if(fontselected.equals("Arial"))
				{
					jtx.setFont(new Font("Arial",Font.BOLD,fonts));
				}
				
				if(fontselected.equals("Elephant"))
				{
					jtx.setFont(new Font("Elephant",Font.BOLD,fonts));
				}
				
				if(fontselected.equals("Consolas"))
				{
					jtx.setFont(new Font("Consolas",Font.BOLD,fonts));
				}
			}
		});
		
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getActionCommand()=="New")
			jtx.setText("");
		
		if(ae.getActionCommand()=="Open")
		{
			 JFileChooser fc=new JFileChooser();    
			    int i=fc.showOpenDialog(this);    
			    if(i==JFileChooser.APPROVE_OPTION){    
			        File f=fc.getSelectedFile();    
			        String filepath=f.getPath();    
			        try{  
			        BufferedReader br=new BufferedReader(new FileReader(filepath));    
			        String s1="",s2="";                         
			        while((s1=br.readLine())!=null){    
			        s2+=s1+"\n";    
			        }    
			        jtx.setText(s2);    
			        br.close();    
			        }catch (Exception ex) {ex.printStackTrace();  }                 
			    }    
		}
		
		
		if(ae.getActionCommand()=="Save")
		{
			JFileChooser saveFile = new JFileChooser();
			int i=saveFile.showSaveDialog(this);
			if(i==JFileChooser.APPROVE_OPTION)
			{
				try
				{
					BufferedWriter fileWriter = new BufferedWriter(new FileWriter(saveFile.getSelectedFile().getPath()));
					fileWriter.write(jtx.getText());
					fileWriter.close();
					
					JOptionPane.showMessageDialog(null, "File Saved");
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}	
		}
		
		
		//about window
		if(ae.getActionCommand()=="About")
		{
			About a1 = new About();
		}
		
		if(ae.getActionCommand()=="Cut")
		{
			selectedtext = jtx.getSelectedText();
			jtx.replaceSelection("");
		}
		
		if(ae.getActionCommand()=="Copy")
		{
			selectedtext = jtx.getSelectedText();
		}
		
		if(ae.getActionCommand()=="Paste")
		{
			jtx.setText(jtx.getText().toString()+selectedtext);
		}
		
		if(ae.getActionCommand()=="Exit")
		{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Previous Program First?","Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION)
			{
			  // Saving code here
				JFileChooser saveFile = new JFileChooser();
				int i=saveFile.showSaveDialog(this);
				if(i==JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedWriter fileWriter = new BufferedWriter(new FileWriter(saveFile.getSelectedFile().getPath()));
						fileWriter.write(jtx.getText());
						fileWriter.close();
						
						JOptionPane.showMessageDialog(null, "File Saved");
						System.exit(1);
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
			else
			{
				System.exit(1);
			}
		}
	}
	
}
