package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import character.Dragon;
import character.Environment;
import character.Fireball;
import character.Wave;
import event.Event;

public class Game extends JPanel implements KeyListener, MouseListener{
	
	int gameSeed = 30;
	//long lastPress =0;
	
	private static final long serialVersionUID = 1L;
	
	private static int speed = 50, dragonSize = 60 ,waveHeight = 50;
	private static int base=400,xStart = 1000;
	private long point = 0,lastPress=0;
	
	private Dragon dragon = new Dragon(100,base-50);
	static Display display;
	
	//Dragon dragon = new Dragon(50,300,50,100);
	Wave[] waveSet = makeWaveSet(4);
	
	ArrayList<Fireball> fireball2 = new ArrayList<>();
	//Wave wave = new Wave(800,300,30,40,30,this);
	//private int waveNumber;
	//private Wave[] waveSet;
	
	private Environment[] envSet = makeEnv(2,Environment.CLOUD);
	private Environment building = new Environment(xStart-100,base-150,this,Environment.BUILDING,4);
	
	public Game() {
		this.setBounds(0,0,1000,600);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setLayout(null);
		this.addMouseListener(this);
	}
	
	public void play() {
		
	}
	public void paint(Graphics g) { 
		super.paint(g);
		Graphics2D g2 =(Graphics2D) g;
		//backgroud
		try {
			this.drawBackground(g2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g2.setColor(Color.blue);
		drawDogHealth(g2);
		g2.drawImage(dragon.getImage(),dragon.x,dragon.y, dragonSize, dragonSize, null);
		//g2.drawRect(dragon.x,dragon.y,dragon.dragonSize,dragon.dragonSize);
		
		g2.drawString(dragon.health +"%",40, 40);
		
		g2.setColor(Color.black);
		//g2.drawRect(wave.x, wave.y, wave.width, wave.height);
		for(Wave wave : waveSet){
			//g2.drawRect(wave.x, wave.y, wave.width, wave.height);
//			g2.drawImage(wave.getImage(),wave.x ,(wave.y-waveHeight),40,waveHeight+10,null);
			g2.drawImage(wave.getImage(),wave.x ,wave.y-20 ,50 ,waveHeight+20, null);
			if(Event.checkHit(dragon, wave, 50, 50)) {
				g2.setStroke(new BasicStroke(10.0f));
				g2.setColor(Color.red);
				g2.drawRect(0, 0, 1000, 900);
				dragon.health -= 5;
				if(dragon.health<=0) {
					System.out.print("Died........");
//					display.endGame(this.point);
//					dragon.health = new Dragon().health;
//					this.point = 0;	
				}
//			drawWave(item,g2);
		}}
		
		for(Fireball fb : fireball2) {
			fb.draw(g);
		}
	
	}
	
	private Wave[] makeWaveSet(int waveNumber) {
		Wave[] waveSet = new Wave[waveNumber];
		for(int i=0;i<waveNumber;i++) {
			double waveLocation = 1000+Math.floor(Math.random()*1000);
			waveSet[i] = new Wave((int)waveLocation,310,30,40,30,this);
		}
		return waveSet;
		
	}
	
	private Environment[] makeEnv(int size,int eType){
		Environment[] envSet = new Environment[size];
		int far = 0;
		for(int i=0;i<size;i++) {
			envSet[i] = new Environment(xStart+far,20,this,eType,10);
			far+=600;
		}
		return envSet;
	}
	
	
	private void drawBackground(Graphics2D g2) throws IOException {
		g2.drawImage(ImageIO.read(new File("img\\sky.png")),0,0,2000,1000, null);
		g2.drawImage(building.getImage(), building.x, building.y, 500, 200, null);
		g2.drawImage(ImageIO.read(new File("img\\dir.png")),0,base+10,2000,220, null);
		for(Environment item:envSet) {
			g2.drawImage(item.getImage(),item.x,item.y,250,160, null);
		}
	}
	
	private void drawWave(Wave wave,Graphics2D g2) {
		g2.drawImage(wave.getImage(),wave.x ,(wave.y-waveHeight),40,waveHeight+10,null);
		if(Event.checkHit(dragon,wave,dragonSize,waveHeight)){
				g2.setColor(new Color(241, 98, 69));
				g2.fillRect(0, 0,1000,1000);			
//				dragon.health-= 150;
				if(dragon.health<=0) {
					display.endGame(this.point);
					dragon.health = new Dragon().health;
					this.point = 0;	
				}
		}
}
	
	private void drawDogHealth(Graphics2D g2) {
		try {
			g2.drawImage(ImageIO.read(new File("img\\heart.png")),10,20, 20,20,null);
			g2.setStroke(new BasicStroke(18.0f));
			g2.setColor(new Color(241, 98, 69));
			g2.drawLine(60, 30,60+dragon.health,30);	
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(6.0f));
			g2.drawRect(50,20, 200,20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis()-lastPress>100) {
//			System.out.println(e.getKeyCode())  ;
			if(e.getKeyCode()== 38 || e.getKeyCode() == 32) {
				dragon.jump(this);
				this.repaint();
		}
		 lastPress = System.currentTimeMillis();		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Fireball b = new Fireball(dragon.x +50,dragon.y,100,this);
		
		fireball2.add(b);
		System.out.println(e.getX() +" "+e.getY() );
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}