package com.skanderj.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.skanderj.gingerbread.Process;
import com.skanderj.gingerbread.core.Game;
import com.skanderj.gingerbread.display.Window;
import com.skanderj.gingerbread.input.Keyboard;
import com.skanderj.gingerbread.input.Mouse;

public class Pong extends Game {
	public static final String IDENTIFIER = "Pong";
	public static final double RATE = 60.0D;

	public static final int OG = 0, WIDTH = 800, HEIGHT = 600;
	public static final int PADDLE_WIDTH = 12, PADDLE_HEIGHT = 60;

	private Window window;
	private Keyboard keyboard;
	private Mouse mouse;

	private Paddle leftPaddle, rightPaddle;

	public Pong() {
		super(Pong.IDENTIFIER, Pong.RATE);
		this.window = new Window(this, Pong.IDENTIFIER, Pong.WIDTH, Pong.HEIGHT);
		this.keyboard = new Keyboard();
		this.mouse = new Mouse();
		this.leftPaddle = new Paddle(10, 10, PADDLE_WIDTH, PADDLE_HEIGHT, Color.WHITE);
		this.rightPaddle = new Paddle(WIDTH - PADDLE_WIDTH - 10, HEIGHT - PADDLE_HEIGHT - 10, PADDLE_WIDTH, PADDLE_HEIGHT, Color.WHITE);
	}

	@Override
	protected void create() {
		this.window.registerKeyboard(this.keyboard);
		this.window.registerMouse(this.mouse);
		this.window.show();
	}

	@Override
	protected void destroy() {
		this.window.hide();
		System.exit(Process.EXIT_SUCCESS);
	}

	@Override
	public void update(double delta) {
		// TODO
		boolean isCloseRequested = this.window.isCloseRequested();
		if (isCloseRequested) {
			this.stop();
		}
		this.leftPaddle.update();
		this.rightPaddle.update();
	}

	@Override
	public void render() {
		BufferStrategy bufferStrategy = this.window.getBufferStrategy(2);
		Graphics graphics = bufferStrategy.getDrawGraphics();
		{
			graphics.setColor(Color.BLACK);
			graphics.fillRect(Pong.OG, Pong.OG, Pong.WIDTH, Pong.HEIGHT);
			// TODO draw
			this.leftPaddle.render(graphics);
			this.rightPaddle.render(graphics);
		}
		graphics.dispose();
		bufferStrategy.show();
	}

	public static void main(String[] args) {
		new Pong().start();
	}
}
