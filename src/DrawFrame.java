/*
 * Andrew Chow
 * CS 111B
 * 7/27/2016
 * This programs purpose is to create a simple drawing gui allowing the user to pick
 * between 3 colors and draw on the panel provided.
 * The user also has the option of clearing their drawing or erasing it using an eraser.
 */

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DrawFrame extends JFrame
{
	private JButton clearButton;
	private JRadioButton blackButton,blueButton,greenButton,eraseButton;
	private JPanel controlPanel;
	private JLabel changeColor;
	
	private ArrayList<ColoredPoints> colorP = new ArrayList<ColoredPoints>();	
	private ColoredPoints colorPoints;	
	private Color c;
	private DrawGui g;
	
	// Main frame with the options panel on the bottom
	public DrawFrame ()
	{
		super("DRAW DAT.");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.white);
		setResizable(false);
		setSize(500,500);
		
		g = new DrawGui();				
		
		blackButton = new JRadioButton("Black",true);
		blackButton.addActionListener(new ButtonListener());
		
		blueButton = new JRadioButton("Blue");
		blueButton.addActionListener(new ButtonListener());
		
		greenButton = new JRadioButton("Green");
		greenButton.addActionListener(new ButtonListener());
		
		eraseButton = new JRadioButton("Eraser");
		eraseButton.addActionListener(new ButtonListener());
		
		ButtonGroup group = new ButtonGroup();
		group.add(blackButton);
		group.add(blueButton);
		group.add(greenButton);
		group.add(eraseButton);
		group.add(clearButton);
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ButtonListener());
					
		changeColor = new JLabel("Options :");
		
		controlPanel = new JPanel();
		controlPanel.add(changeColor);
		controlPanel.add(blackButton);
		controlPanel.add(blueButton);
		controlPanel.add(greenButton);
		controlPanel.add(eraseButton);
		controlPanel.add(clearButton);
		
		contentPane.add(g, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	// Changes pen color based on button press
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(blackButton.isSelected())
				c = Color.BLACK;
			else if(blueButton.isSelected())
				c = Color.BLUE;
			else if(greenButton.isSelected())
				c = Color.GREEN;		
			else if(eraseButton.isSelected())
				c= Color.WHITE;
			if(event.getSource() == clearButton)
			{
				colorP.clear();
				repaint();
			}
		}		
	}	
	// the DrawGui panel where the user can draw.
	public class DrawGui extends JPanel
	{
		private boolean drawOn = false;		
		private JLabel optionsLabel = new JLabel("");
		
		public DrawGui () 
		{
			setBackground(Color.WHITE);
			setSize(500,500);
			
			this.add(optionsLabel);
			this.addMouseListener(new MyMouseClass());
			this.addMouseMotionListener(new MyMouseClass());				
		}
		
		// Paints a circle at each point where the cursor is, close enough it resembles a line
		@Override
		public void paintComponent(Graphics pen) 
		{
			super.paintComponent(pen);

			for(int i  = 0; i < colorP.size() ; i++)
			{	
				pen.setColor(colorP.get(i).getColor());
				
				// calls the ColoredPoints Array, then gets the 
				//ColoredPoints object at index i, then calls getPoint(). Then get X/Y to get the X/Y cordinates
				pen.fillOval((int)colorP.get(i).getPoint().getX(),(int)colorP.get(i).getPoint().getY(), 10, 10);
			}
			
		}	
		// Switches on/off the paint pen
		private class MyMouseClass extends MouseAdapter
		{
			@Override
			public void mouseClicked(MouseEvent event) 
			{			
				if (drawOn)
				{
					drawOn = false;
					optionsLabel.setText("Pen : Off");
				}
				else 
				{
					drawOn = true;
					optionsLabel.setText("Pen : On");
				}
			}		
			@Override
			
			//Stores current Color/Point to an ColoredPoint object
			public void mouseMoved(MouseEvent event) 
			{
				if(drawOn)
				{
					colorPoints = new ColoredPoints(event.getPoint(),c);
					colorP.add(colorPoints);
					repaint();							
				}
			}		
		}		
	}
	
	public static void main(String args[]) 
	{		
		DrawFrame frame = new DrawFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
