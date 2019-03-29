package com.skanderj.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.skanderj.gingerbread.core.Game;

public class Ball {
	protected final Pong game;

	protected int x, y;
	protected final int radius;
	protected double horizontalVelocity, verticalVelocity;
	protected Color color;

	protected Rectangle boundingBox;

	public Ball(Pong game, int x, int y, int radius, Color color) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.verticalVelocity = 0.0;
		this.horizontalVelocity = 0.0;
		this.color = color;
		this.boundingBox = new Rectangle(this.x, this.y, this.radius, this.radius);
	}

	public void update() {
		if ((this.y < 0) || ((this.y + this.radius) > Pong.HEIGHT)) {
			this.verticalVelocity = -this.verticalVelocity;
		}
		Paddle leftPaddle = this.game.leftPaddle, rightPaddle = this.game.rightPaddle;
		if (this.boundingBox.intersects(leftPaddle.boundingBox) && (this.horizontalVelocity < 0)) {
			this.horizontalVelocity = -this.horizontalVelocity + 0.1;
			this.verticalVelocity += 0.05;
		}
		if (this.boundingBox.intersects(rightPaddle.boundingBox) && (this.horizontalVelocity > 0)) {
			this.horizontalVelocity = -this.horizontalVelocity - 0.1;
			this.verticalVelocity += 0.05;
		}
		if (this.x <= 0) {
			this.game.rightScore += 1;
			this.resetPosition();
			this.randomLaunch();
		}
		if ((this.x + this.radius) >= Pong.WIDTH) {
			this.game.leftScore += 1;
			this.resetPosition();
			this.randomLaunch();
		}
		this.x += (int) this.horizontalVelocity;
		this.y += (int) this.verticalVelocity;
		this.boundingBox.setBounds(this.x, this.y, this.radius, this.radius);
		System.out.println("x: " + this.x + ", y: " + this.y + ", dx: " + this.horizontalVelocity + ", dy: " + this.verticalVelocity);
	}

	public void render(Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fillOval(this.x, this.y, this.radius, this.radius);
		if (Pong.DEBUG) {
			graphics.setColor(Color.RED);
			graphics.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
		}
	}

	public void resetPosition() {
		this.x = (Pong.WIDTH / 2) - (Pong.BALL_RADIUS / 2);
		this.y = (Pong.HEIGHT / 2) - (Pong.BALL_RADIUS / 2);
		this.horizontalVelocity = 0;
		this.verticalVelocity = 0;
	}

	public void randomLaunch() {
		Random random = new Random();
		int verticalDirection = random.nextInt(2);
		int horizontalDirection = random.nextInt(2);
		if (verticalDirection == 0) {
			this.verticalVelocity = 3;
		} else {
			this.verticalVelocity = -3;
		}
		if (horizontalDirection == 0) {
			this.horizontalVelocity = 3;
		} else {
			this.horizontalVelocity = -3;
		}
	}

	public Game getGame() {
		return this.game;
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
