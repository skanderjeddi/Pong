package com.skanderj.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	private int x, y;
	private final int radius;
	private double horizontalVelocity, verticalVelocity;
	private Color color;

	private Rectangle boundingBox;

	public Ball(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.verticalVelocity = 0.0;
		this.horizontalVelocity = 0.0;
		this.color = color;
		this.boundingBox = new Rectangle(x - radius, y - radius, radius * 2, radius * 2);
	}

	public void update() {
		this.x += this.horizontalVelocity;
		this.y += this.verticalVelocity;
		this.boundingBox.setBounds(this.x - this.radius, this.y - this.radius, this.radius * 2, this.radius * 2);
	}

	public void render(Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fillOval(this.x, this.y, this.radius, this.radius);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getRadius() {
		return this.radius;
	}

	public double getVerticalVelocity() {
		return this.verticalVelocity;
	}

	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}

	public Color getColor() {
		return this.color;
	}

	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}

	public void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}

	public void setHorizontalVelocity(double horizontalVelocity) {
		this.horizontalVelocity = horizontalVelocity;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
