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
	double distance=0;
	double selHeight,selVelocity,selAngle;
	int outAngle,outVelocity;
	JSlider height,velocity,angle;
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
		panel.setPreferredSize(new Dimension(800,625));
		panel.setBackground(Color.WHITE);
		
		height = new JSlider(150,300);
		height.setMajorTickSpacing(20);
		height.setMinorTickSpacing(10);
		height.setBounds(260, 400, 250, 50);
		height.setPaintLabels(true);
		height.setPaintTicks(true);		
		panel.add(height);
		
		velocity = new JSlider(200,1000);
		velocity.setMajorTickSpacing(200);
		velocity.setMinorTickSpacing(20);
		velocity.setBounds(100, 475, 250, 50);
		velocity.setPaintLabels(true);
		velocity.setPaintTicks(true);		
		panel.add(velocity);
		
		angle = new JSlider(1,89);
		angle.setMajorTickSpacing(10);
		angle.setMinorTickSpacing(5);
		angle.setBounds(400, 475, 250, 50);
		angle.setPaintLabels(true);
		angle.setPaintTicks(true);
		panel.add(angle);
		
		JButton angleButton = new JButton("Calculate Angle");
		angleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getValues()) {
					calculateAngle();
				}
				panel.repaint();
			}
		});
		angleButton.setBounds(100, 550, 250,25);
		panel.add(angleButton);
		
		JButton velocityButton = new JButton("Calculate Velocity");
		velocityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getValues()) {
					calculateVelocity();
				}
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
					double pixy = me.getY()-basketY;
					double pixx = me.getX()-basketX;
					double x = (pixx/640.0)*28.7;
					double y = (pixy/372.0)*15.2;
					distance=(Math.sqrt(x*x+y*y));
				//	System.out.println(distance);
					outAngle=0;
					outVelocity=0;
					panel.repaint();
				}
			}});
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel.repaint();
	}
	public void calculateAngle() {
		double calcAngle;
		double vSquared=selVelocity*selVelocity;
		double rad = Math.atan((selVelocity+((Math.sqrt((vSquared*vSquared)-(2.0*9.8*selHeight*vSquared)+(9.8*distance*distance))/(9.8*distance)))));
		calcAngle=Math.toDegrees(rad);
		outAngle=(int) calcAngle;
		panel.repaint();
		//System.out.println(rad);
		//System.out.println(calcAngle);
		
	}

	public void calculateVelocity() {
		double calcVelocity;
		double rad=Math.toRadians(selAngle);
		double tan=Math.tan(rad);	
		double num=(9.8*distance*distance)*(1.0+tan*tan);
		double den=-2.0*(selHeight-distance*tan-2.0*selHeight);
		calcVelocity=Math.sqrt(num/den);
		outVelocity=(int) calcVelocity;
		//System.out.println(num);
		//System.out.println(den);
		//System.out.println(calcVelocity);
	}
	private boolean getValues() {
		if(distance==0) {
			return false;
		}
		selHeight=height.getValue()/100.0;
		selAngle=angle.getValue();
		selVelocity=velocity.getValue()/100.0;
		
		//System.out.println("h:"+selHeight);
		//System.out.println("a:"+selAngle);
		//System.out.println("v:"+selVelocity);

		return true;
		
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
		g.drawString("Velocity (cm/s)", 150, 475);
		g.drawString("Release Height (cm)", 275, 400);		
		g.setFont(new Font("SansSerif", 1, 19));
		g.drawString("Distance: "+(int)distance+" m", 75, 410);
		g.drawString("Calculated Angle: "+outAngle+" degrees", 80, 600);
		g.drawString("Calculated Velocity: "+outVelocity+" m/s", 400, 600);
	}
	

}
