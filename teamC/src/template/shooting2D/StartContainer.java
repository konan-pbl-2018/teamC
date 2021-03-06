package template.shooting2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTContainer;
import framework.RWT.RWTImage;
import framework.RWT.RWTLabel;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;
import framework.audio.BGM3D;
import framework.audio.Sound3D;

public class StartContainer extends RWTContainer {
	private TemplateShooting2DMultiStates game;
	private Sound3D startBGM = BGM3D.registerBGM("data\\sozai\\BGM\\start.wav");

	public StartContainer(TemplateShooting2DMultiStates game) {
		this.game = game;
	}

	@Override
	public void build(GraphicsConfiguration gc) {
		BGM3D.playBGM(startBGM);

		RWTImage backgroud = new RWTImage("data\\sozai\\haikeidesu.png");
		backgroud.setRelativePosition(0.0f, 0.0f);
		backgroud.setSize(960, 960);
		addWidget(backgroud);

		RWTLabel titleLabel = new RWTLabel();
		titleLabel.setString("RPG Shooting");
		titleLabel.setRelativePosition(0.05f, 0.4f);
		Font titlefont = new Font("", Font.PLAIN, 60);
		titleLabel.setColor(Color.WHITE);
		titleLabel.setFont(titlefont);
		addWidget(titleLabel);

		RWTLabel startLabel = new RWTLabel();
		startLabel.setString("Press B");
		startLabel.setRelativePosition(0.35f, 0.7f);
		Font startfont = new Font("", Font.PLAIN, 40);
		startLabel.setColor(Color.WHITE);
		startLabel.setFont(startfont);
		addWidget(startLabel);

		repaint();
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			Sound3D sound = new Sound3D("data\\sozai\\BGM\\start_button.wav");
			sound.play();
			game.play();
		}
	}

	@Override
	public void keyReleased(RWTVirtualKey key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(RWTVirtualKey key) {
		// TODO Auto-generated method stub

	}

}