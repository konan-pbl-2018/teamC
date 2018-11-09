package template.shooting2D;

import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTContainer;
import framework.RWT.RWTImage;
import framework.RWT.RWTLabel;
import framework.RWT.RWTLine;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;

public class RPGContainer extends RWTContainer {
	private TemplateShooting2DMultiStates game;

	public RPGContainer(TemplateShooting2DMultiStates game) {
		this.game = game;
	}
	int x = 10;
	int Turn = 0;
	int BossHp = 3000;
	int BossAtack = 70;
	int MyHp = 500;
	int Zanki = 5;
	int Item = 5;

	@Override
	public void build(GraphicsConfiguration gc) {

		RWTLabel AttackLabel = new RWTLabel();
		AttackLabel.setString("W:攻撃");//RWTLabel.NEW_PARAGRAPH
		AttackLabel.setRelativePosition(0.05f, 0.85f);
		Font f1 = new Font("", Font.PLAIN, 30);
		AttackLabel.setFont(f1);
		addWidget(AttackLabel);

		RWTLabel KaihukuLabel = new RWTLabel();
		KaihukuLabel.setString("D:回復 × " + Item);
		KaihukuLabel.setRelativePosition(0.35f, 0.85f);
		KaihukuLabel.setFont(f1);
		addWidget(KaihukuLabel);

		RWTLabel ZibakuLabel = new RWTLabel();
		ZibakuLabel.setString("A:自爆");
		ZibakuLabel.setRelativePosition(0.05f, 0.95f);
		ZibakuLabel.setFont(f1);
		addWidget(ZibakuLabel);

		RWTLabel EscapeLabel = new RWTLabel();
		EscapeLabel.setString("S:諦める");
		EscapeLabel.setRelativePosition(0.35f, 0.95f);
		EscapeLabel.setFont(f1);
		addWidget(EscapeLabel);

		RWTLabel ZankiLabel = new RWTLabel();
		ZankiLabel.setString("残機 × " + Zanki);
		ZankiLabel.setRelativePosition(0.7f, 0.95f);
		ZankiLabel.setFont(f1);
		addWidget(ZankiLabel);

		RWTLabel MyHpLabel = new RWTLabel();
		MyHpLabel.setString("HP: " + MyHp + "/500");
		MyHpLabel.setRelativePosition(0.1f, 0.65f);
		Font f2 = new Font("", Font.PLAIN, 20);
		MyHpLabel.setFont(f2);
		addWidget(MyHpLabel);

		RWTLabel BossHpLabel = new RWTLabel();
		BossHpLabel.setString("BossHP: " + BossHp + "/3000");
		BossHpLabel.setRelativePosition(0.6f, 0.45f);
		BossHpLabel.setFont(f2);
		addWidget(BossHpLabel);

		RWTLabel QLabel = new RWTLabel();
		QLabel.setString("どうする？");
		QLabel.setRelativePosition(0.05f, 0.76f);
		QLabel.setFont(f2);
		addWidget(QLabel);



		RWTLine line1 = new RWTLine();
		line1.setRelativePosition(0.02f, 0.7f, 0.98f, 0.7f);
		addWidget(line1);

		RWTLine line2 = new RWTLine();
		line2.setRelativePosition(0.02f, 0.98f, 0.98f, 0.98f);
		addWidget(line2);

		RWTLine line3 = new RWTLine();
		line3.setRelativePosition(0.02f, 0.7f, 0.02f, 0.98f);
		addWidget(line3);

		RWTLine line4 = new RWTLine();
		line4.setRelativePosition(0.98f, 0.7f, 0.98f, 0.98f);
		addWidget(line4);

		RWTLine line5 = new RWTLine();
		line5.setRelativePosition(0.02f, 0.78f, 0.98f, 0.78f);
		addWidget(line5);

		RWTImage MyImage = new RWTImage("data\\images\\MyShip.gif");
		MyImage.setRelativePosition(0.17f, 0.5f);
		addWidget(MyImage);

		RWTImage BossImage = new RWTImage("data\\images\\boss.png");
		BossImage.setRelativePosition(0.57f, 0.05f);
		addWidget(BossImage);
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {

		// 上　戦う
		if (key.getVirtualKey() == RWTVirtualController.UP) {
			BossHp -= 50;
			System.out.println(BossHp);
		}
		// 下　あきらめる
		if (key.getVirtualKey() == RWTVirtualController.DOWN) {

			System.out.println(x);
		}
		// 右　回復
		if (key.getVirtualKey() == RWTVirtualController.RIGHT) {
			Item -=1;
			MyHp += 200;
			if(MyHp > 500) {
				MyHp = 500;
			}
			System.out.println(MyHp + Item);
		}
		// 左　自爆
		if (key.getVirtualKey() == RWTVirtualController.LEFT) {
			Zanki -= 1;
			BossHp -= 400;
			System.out.println(x);
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
