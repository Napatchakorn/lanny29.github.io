package character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Dragon {
	public int x ;
	public int y;
	public int health=10;
	public int jumpHigh = 100;
	ImageIcon icon;
	
	public Dragon() {
		
	}
	
	public Dragon(int x,int y) {
		this.x = x;
		this.y = y;
//		this.dragonSize = dragonSize;
//		this.health = health;
	}
	public void jump(JPanel game) {
		this.y -= jumpHigh;
		game.repaint();
		Timer timer = new Timer(200, new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				y+= jumpHigh;
				game.repaint();
			}	
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("img\\mario4.png"));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	

}