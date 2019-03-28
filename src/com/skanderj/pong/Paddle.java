package com.skanderj.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
	private final int x, width, height;
	private int y;
	private double velocity;
	private Color color;

	// Collisions
	private Rectangle boundingBox;

	public Paddle(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocity = 0.0;
		this.color = color;
		this.boundingBox = new Rectangle(x, y, width, height);
	}

	public void update() {
		this.y += this.velocity;
		this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
	}

	public void render(Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fillRect(this.x, this.y, this.width, this.height);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public double getVelocity() {
		return this.velocity;
	}

	public Color getColor() {
		return this.color;
	}

	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
