package com.skanderj.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
	protected final int x, width, height;
	protected int y;
	protected double velocity;
	protected Color color;

	// Collisions
	protected Rectangle boundingBox;

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
		if (this.y < 0) {
			this.y = 0;
		}
		if ((this.height + this.y) > Pong.HEIGHT) {
			this.y = Pong.HEIGHT - this.height;
		}
		this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
	}

	public void render(Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fillRect(this.x, this.y, this.width, this.height);
		if (Pong.DEBUG) {
			graphics.setColor(Color.RED);
			graphics.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
		}
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
