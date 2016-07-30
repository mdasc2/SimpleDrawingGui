/*
 * Andrew Chow
 * CS 111B
 * 7/27/2016
 * A class that stores Points/Colors in order to use for drawing
 * */
import java.awt.Color;
import java.awt.Point;

public class ColoredPoints
{
	private Point currentPoint;
	private Color currentColor;
	private static final Color DEFAULT_COLOR = Color.WHITE;
	private static final Point DEFAULT_POINT = new Point(0,0);
	
	public ColoredPoints()
	{
		currentPoint = DEFAULT_POINT;
		currentColor = DEFAULT_COLOR;
	}
	
	public ColoredPoints(Point currentPoint,Color currentColor)
	{
		this.currentPoint = currentPoint;
		this.currentColor = currentColor;		
	}
	
	public Point getPoint()
	{
		return this.currentPoint;
	}
	
	public void setPoint(Point currentPoint)
	{
		this.currentPoint = currentPoint;
	}
	
	public Color getColor()
	{
		return this.currentColor;
	}
	
	public void setColor(Color currentColor)
	{
		this.currentColor = currentColor;
	}		
}
