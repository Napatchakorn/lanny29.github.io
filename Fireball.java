package character;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.Game;

public class Fireball {
	public int x ,y,speed;
	public int size = 30;
	private int xStart = x;
	Color c = Color.blue;
	public Fireball(int x,int y,int speed,JPanel game){
		this.x = x; 
		this.y = y;
		this.speed = speed;
		this.xStart = x;
		move(game);
	
	}
	
	public void draw(Graphics g) {	
		Color color = new Color(255, 200, 113);
		g.setColor(color);
		g.fillOval(x, y, size, size);
	}

	public void move(JPanel game) {
		Timer timer = new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				x += speed;
				game.repaint();
			}
			
		});
		timer.start();
		
	}
	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("img\\fire1.png"));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	}

