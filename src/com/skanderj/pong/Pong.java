package com.skanderj.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import com.skanderj.gingerbread.Process;
import com.skanderj.gingerbread.core.Game;
import com.skanderj.gingerbread.display.Window;
import com.skanderj.gingerbread.input.Keyboard;
import com.skanderj.gingerbread.input.Mouse;

public class Pong extends Game {
	public static final String IDENTIFIER = "Pong";
	public static final double RATE = 60.0D;
	public static final boolean DEBUG = true;

	public static final int OG = 0, WIDTH = 800, HEIGHT = 600;
	public static final int PADDLE_WIDTH = 12, PADDLE_HEIGHT = 60, BALL_RADIUS = 10;

	private final Window window;
	private final Keyboard keyboard;
	private final Mouse mouse;

	protected final Paddle leftPaddle, rightPaddle;
	protected final Ball ball;

	public Pong() {
		super(Pong.IDENTIFIER, Pong.RATE);
		this.window = new Window(this, Pong.IDENTIFIER, Pong.WIDTH, Pong.HEIGHT);
		this.keyboard = new Keyboard();
		this.mouse = new Mouse();
		this.leftPaddle = new Paddle(10, 10, Pong.PADDLE_WIDTH, Pong.PADDLE_HEIGHT, Color.WHITE);
		this.rightPaddle = new Paddle(Pong.WIDTH - Pong.PADDLE_WIDTH - 10, Pong.HEIGHT - Pong.PADDLE_HEIGHT - 10, Pong.PADDLE_WIDTH, Pong.PADDLE_HEIGHT, Color.WHITE);
		this.ball = new Ball(this, (Pong.WIDTH / 2) - (Pong.BALL_RADIUS / 2), (Pong.HEIGHT / 2) - (Pong.BALL_RADIUS / 2), Pong.BALL_RADIUS, Color.WHITE);
	}

	@Override
	protected void create() {
		this.ball.setVerticalVelocity(2);
		this.ball.setHorizontalVelocity(2);
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
		// Handle keyboard input
		{
			// Left paddle
			boolean zPressed = this.keyboard.isKeyDown(KeyEvent.VK_Z);
			boolean sPressed = this.keyboard.isKeyDown(KeyEvent.VK_S);
			if (zPressed && !sPressed) {
				this.leftPaddle.setVelocity(this.leftPaddle.getVelocity() - 0.2);
			} else if (sPressed && !zPressed) {
				this.leftPaddle.setVelocity(this.leftPaddle.getVelocity() + 0.2);
			} else {
				this.leftPaddle.setVelocity(0.0);
			}
			// Right paddle
			boolean upPressed = this.keyboard.isKeyDown(KeyEvent.VK_UP);
			boolean downPressed = this.keyboard.isKeyDown(KeyEvent.VK_DOWN);
			if (upPressed && !downPressed) {
				this.rightPaddle.setVelocity(this.rightPaddle.getVelocity() - 0.2);
			} else if (downPressed && !upPressed) {
				this.rightPaddle.setVelocity(this.rightPaddle.getVelocity() + 0.2);
			} else {
				this.rightPaddle.setVelocity(0.0);
			}
		}
		this.leftPaddle.update();
		this.rightPaddle.update();
		this.ball.update();
		this.keyboard.update();
		this.mouse.update();
	}

	@Override
	public void render() {
		BufferStrategy bufferStrategy = this.window.getBufferStrategy(2);
		Graphics graphics = bufferStrategy.getDrawGraphics();
		{
			graphics.setColor(Color.BLACK);
			graphics.fillRect(Pong.OG, Pong.OG, Pong.WIDTH, Pong.HEIGHT);
			this.increaseRenderQuality(graphics);
			// Center line
			graphics.setColor(Color.RED);
			graphics.drawLine(Pong.WIDTH / 2, 0, Pong.WIDTH / 2, Pong.HEIGHT);
			// TODO draw
			this.leftPaddle.render(graphics);
			this.rightPaddle.render(graphics);
			this.ball.render(graphics);
		}
		graphics.dispose();
		bufferStrategy.show();
	}

	public void increaseRenderQuality(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}

	public Window getWindow() {
		return this.window;
	}

	public Keyboard getKeyboard() {
		return this.keyboard;
	}

	public Mouse getMouse() {
		return this.mouse;
	}

	public Paddle getLeftPaddle() {
		return this.leftPaddle;
	}

	public Paddle getRightPaddle() {
		return this.rightPaddle;
	}

	public Ball getBall() {
		return this.ball;
	}

	public static void main(String[] args) {
		new Pong().start();
	}
}
