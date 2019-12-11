import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BballSim {
	JPanel panel;
	private JFrame frame = new JFrame("Basketball Shot Simulator");
	int distance;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BballSim().start();
	}

	private void start() {
		// TODO Auto-generated method stub
		makeFrame();
	}

	private void makeFrame() {
		// TODO Auto-generated method stub
		panel  = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				addImage(g);
				addText(g);
				
			}
		};
		frame.add(panel);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800,600));
		JSlider velocity = new JSlider(200,1000);
		velocity.setMajorTickSpacing(200);
		velocity.setBounds(100, 475, 250, 50);
		velocity.setPaintLabels(true);
		velocity.setPaintTicks(true);		
		panel.add(velocity);
		JSlider angle = new JSlider(1,89);
		angle.setMajorTickSpacing(10);
		angle.setBounds(400, 475, 250, 50);
		angle.setPaintLabels(true);
		angle.setPaintTicks(true);
		panel.add(angle);
		JButton angleButton = new JButton("Calculate Angle");
		angleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calculateAngle();
				panel.repaint();
			}
		});
		angleButton.setBounds(100, 550, 250,25);
		panel.add(angleButton);
		JButton velocityButton = new JButton("Calculate Velocity");
		velocityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calculateVelocity();
				panel.repaint();
			}
		});
		velocityButton.setBounds(400, 550, 250,25);
		panel.add(velocityButton);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if(me.getX()>50&&me.getX()<690&&me.getY()>50&&me.getY()<422) {
					final int basketY=236;
					final int basketX=0;
					int y = me.getY()-basketY;
					int x = me.getX()-basketX;
					distance=(int) Math.sqrt(x*x+y*y);
					System.out.println(distance);
				}
			}});
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel.repaint();
	}
	public void calculateAngle() {
		
	}
	public void calculateVelocity() {
		
	}
	public void addImage(Graphics g) {
		g.drawImage(getImage("court.png"), 50, 0, 640, 372, null);
	}
	protected Image getImage(String fn) {
		Image img = null;
		try {
			img = ImageIO.read(this.getClass().getResource(fn));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	public void addText(Graphics g) {
		g.setFont(new Font("SansSerif", 1, 20));
		g.drawString("Angle", 490, 475);
		g.drawString("Velocity", 190, 475);
	}
	

}
