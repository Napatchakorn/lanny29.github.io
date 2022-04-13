package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame implements ActionListener{
//	public Display() {
//		super("my game");
//		this.setSize(1000,600);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setLocation(300, 200);
//		this.setVisible(true);
//		this.getContentPane().setLayout(null);
//		//this.getContentPane().add(new Game());
//		this.pack();
//	}
	public static void main(String args[]) {

		JFrame frame = new JFrame("jump jump");
		System.out.println("welcome");
		Game gm = new Game();
		
		frame.add(gm);
		
		
		
		frame.setSize(1000,600);
		frame.setLocation(300, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void removeContent() {
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
	}
	
	public void endGame(long point) {
		removeContent();
		this.getContentPane().add(new Menu(point,this));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}