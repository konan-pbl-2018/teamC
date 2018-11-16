package template.shooting2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTContainer;
import framework.RWT.RWTImage;
import framework.RWT.RWTLabel;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;

public class EndingContainer extends RWTContainer {
	private TemplateShooting2DMultiStates game;

	public EndingContainer(TemplateShooting2DMultiStates game) {
		this.game = game;
	}

	@Override
	public void build(GraphicsConfiguration gc) {
		RWTImage backgroud = new RWTImage("data\\sozai\\saigo.png");
		backgroud.setRelativePosition(0.0f, 0.0f);
		backgroud.setSize(960, 960);
		addWidget(backgroud);

		RWTLabel startLabel = new RWTLabel();
		startLabel.setColor(Color.WHITE);
		startLabel.setString("Clear!!!");
		startLabel.setRelativePosition(0.3f, 0.5f);
		Font f = new Font("", Font.PLAIN, 60);
		startLabel.setFont(f);
		addWidget(startLabel);

		RWTLabel bottanLabel = new RWTLabel();
		bottanLabel.setColor(Color.WHITE);
		bottanLabel.setString("Press B");
		bottanLabel.setRelativePosition(0.4f, 0.7f);
		Font f2 = new Font("", Font.PLAIN, 30);
		bottanLabel.setFont(f2);
		addWidget(bottanLabel);

		repaint();
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			game.restart();
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
