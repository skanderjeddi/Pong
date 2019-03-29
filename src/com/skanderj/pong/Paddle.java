package com.skanderj.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Paddle {
	protected final Pong game;

	protected final int x, width, height;
	protected int y;
	protected double velocity;
	protected Color color;

	// Collisions
	protected Rectangle boundingBox;

	protected boolean aiControlled;

	public Paddle(Pong game, int x, int y, int width, int height, Color color, boolean aiControlled) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocity = 0.0;
		this.color = color;
		this.boundingBox = new Rectangle(x, y, width, height);
		this.aiControlled = aiControlled;
	}

	public void update() {
		if (this.aiControlled) {
			if (((this.game.ball.x >= this.x) && (this.x == 10)) || ((this.game.ball.x <= this.x) && (this.x == (Pong.WIDTH - Pong.PADDLE_WIDTH - 10)))) {
				if (this.game.ball.y < this.y) {
					this.velocity = this.game.ball.getVerticalVelocity() / 1.5;
				}
				if (this.game.ball.y > this.y) {
					this.velocity = this.game.ball.getVerticalVelocity() / 1.5;
				}
			} else {
				this.y = (Pong.HEIGHT / 2) - (this.y / 5);
			}
			this.velocity *= (new Random().nextInt(4) + (7 / 10));
		}
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
		graphics.fillRoundRect(this.x, this.y, this.width, this.height, 0, 0);
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
