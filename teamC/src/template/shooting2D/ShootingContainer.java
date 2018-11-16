package template.shooting2D;

import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTCanvas3D;
import framework.RWT.RWTContainer;
import framework.RWT.RWTLabel;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;

public class ShootingContainer extends RWTContainer {
	private TemplateShooting2DMultiStates game;
	RWTCanvas3D canvas;
	private RWTLabel startLabel = new RWTLabel();

	public ShootingContainer(TemplateShooting2DMultiStates game) {
		this.game = game;
	}

	@Override
	public void build(GraphicsConfiguration gc) {
		if (gc != null) {
			canvas = new RWTCanvas3D(gc);
		} else {
			canvas = new RWTCanvas3D();
		}
		canvas.setRelativePosition(0.0f, 0.1f);
		canvas.setRelativeSize(1.0f, 0.9f);
		addCanvas(canvas);

//		RWTLabel startLabel = new RWTLabel();
		startLabel.setString("*****");
		startLabel.setRelativePosition(0.8f, 0.08f);
		Font f = new Font("", Font.PLAIN, 40);
		startLabel.setFont(f);
		addWidget(startLabel);

		repaint();
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.BUTTON_A) {

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

	public void setStartLabelText(String text) {
		startLabel.setString(text);
		repaint();
	}
}
