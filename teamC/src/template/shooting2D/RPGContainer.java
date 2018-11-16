package template.shooting2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTContainer;
import framework.RWT.RWTImage;
import framework.RWT.RWTLabel;
import framework.RWT.RWTLine;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;
import framework.audio.BGM3D;
import framework.audio.Sound3D;

public class RPGContainer extends RWTContainer {
	private TemplateShooting2DMultiStates game;

	public RPGContainer(TemplateShooting2DMultiStates game) {
		this.game = game;
	}

	int Turn = 6;
	int ending = 0;//エンディングのフラグ
	int angry = 0;//怒り状態のフラグ
	int shibou = 0;//死亡のフラグ
	int BossHp = 2000;
	int BossMaxHp = BossHp;
	int BossAttack = 70;
	int MyHp = 500;
	int MaxMyHp = MyHp;
	int MyAttack = 50;
	int Zanki = 5;
	int Item = 5;

	Sound3D battle = BGM3D.registerBGM("data\\sozai\\BGM\\battle.wav");
	Sound3D muon = BGM3D.registerBGM("data\\sozai\\BGM\\muon.wav");
	Sound3D attack = new Sound3D("data\\sozai\\BGM\\punch-high1.wav");
	Sound3D submachinegun = new Sound3D("data\\sozai\\BGM\\submachinegun1.wav");
	Sound3D kaihuku = new Sound3D("data\\sozai\\BGM\\magic-status-cure1.wav");
	Sound3D bomb = new Sound3D("data\\sozai\\BGM\\bomb1.wav");
	Sound3D monstergrow = new Sound3D("data\\sozai\\BGM\\monster-growl1.wav");
	Sound3D tin = new Sound3D("data\\sozai\\BGM\\tin1.wav");
	Sound3D trumpet = new Sound3D("data\\sozai\\BGM\\trumpet1.wav");

	RWTLabel ZankiLabel = new RWTLabel();
	RWTLabel MyHpLabel = new RWTLabel();
	RWTLabel QLabel = new RWTLabel();
	RWTLabel BossHpLabel = new RWTLabel();


	@Override
	public void build(GraphicsConfiguration gc) {//画面表示

		BGM3D.playBGM(battle);
		monstergrow.play();

		RWTImage BackImage = new RWTImage("data\\sozai\\blackback.png");
		BackImage.setRelativePosition(0f, 0f);
		addWidget(BackImage);
		repaint();

		RWTLabel AttackLabel = new RWTLabel();
		AttackLabel.setString("W:攻撃");//RWTLabel.NEW_PARAGRAPH
		AttackLabel.setRelativePosition(0.05f, 0.85f);
		AttackLabel.setColor(Color.WHITE);
		Font f1 = new Font("", Font.PLAIN, 30);
		AttackLabel.setFont(f1);
		addWidget(AttackLabel);

		RWTLabel KaihukuLabel = new RWTLabel();
		KaihukuLabel.setString("D:回復 × " + Item);
		KaihukuLabel.setRelativePosition(0.35f, 0.85f);
		KaihukuLabel.setColor(Color.WHITE);
		KaihukuLabel.setFont(f1);
		addWidget(KaihukuLabel);

		RWTLabel ZibakuLabel = new RWTLabel();
		ZibakuLabel.setString("A:自爆");
		ZibakuLabel.setRelativePosition(0.05f, 0.95f);
		ZibakuLabel.setColor(Color.WHITE);
		ZibakuLabel.setFont(f1);
		addWidget(ZibakuLabel);

		RWTLabel EscapeLabel = new RWTLabel();
		EscapeLabel.setString("S:諦める");
		EscapeLabel.setRelativePosition(0.35f, 0.95f);
		EscapeLabel.setColor(Color.WHITE);
		EscapeLabel.setFont(f1);
		addWidget(EscapeLabel);


		ZankiLabel.setString("残機 × " + Zanki);
		ZankiLabel.setRelativePosition(0.7f, 0.95f);
		ZankiLabel.setColor(Color.WHITE);
		ZankiLabel.setFont(f1);
		addWidget(ZankiLabel);


		MyHpLabel.setString("HP: " + MyHp + "/" + MaxMyHp);
		MyHpLabel.setRelativePosition(0.1f, 0.65f);
		MyHpLabel.setColor(Color.WHITE);
		Font f2 = new Font("", Font.PLAIN, 20);
		MyHpLabel.setFont(f2);
		addWidget(MyHpLabel);

		//		RWTLabel BossHpLabel = new RWTLabel();
		BossHpLabel.setString("BossHP: " + BossHp + "/" + BossMaxHp);
		BossHpLabel.setRelativePosition(0.6f, 0.45f);
		BossHpLabel.setColor(Color.WHITE);
		BossHpLabel.setFont(f2);
		addWidget(BossHpLabel);


		QLabel.setString("敵のボスがあらわれた");
		QLabel.setRelativePosition(0.05f, 0.76f);
		QLabel.setColor(Color.WHITE);
		QLabel.setFont(f2);
		addWidget(QLabel);

		RWTLine line1 = new RWTLine();
		line1.setRelativePosition(0.02f, 0.7f, 0.98f, 0.7f);
		line1.setColor(Color.WHITE);
		addWidget(line1);

		RWTLine line2 = new RWTLine();
		line2.setRelativePosition(0.02f, 0.98f, 0.98f, 0.98f);
		line2.setColor(Color.WHITE);
		addWidget(line2);

		RWTLine line3 = new RWTLine();
		line3.setRelativePosition(0.02f, 0.7f, 0.02f, 0.98f);
		line3.setColor(Color.WHITE);
		addWidget(line3);

		RWTLine line4 = new RWTLine();
		line4.setRelativePosition(0.98f, 0.7f, 0.98f, 0.98f);
		line4.setColor(Color.WHITE);
		addWidget(line4);

		RWTLine line5 = new RWTLine();
		line5.setRelativePosition(0.02f, 0.78f, 0.98f, 0.78f);
		line5.setColor(Color.WHITE);
		addWidget(line5);

		RWTImage MyImage = new RWTImage("data\\sozai\\自機最終です.png");
		MyImage.setRelativePosition(0f, 0.3f);
		addWidget(MyImage);

		RWTImage BossImage = new RWTImage("data\\images\\boss.png");
		BossImage.setRelativePosition(0.57f, 0.05f);
		addWidget(BossImage);

		repaint();

	}

	public  void update() {
		System.out.println("1");
		repaint();
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {

		// 上　戦う
		if (key.getVirtualKey() == RWTVirtualController.UP && Turn ==0 && ending ==0) {
			repaint();
			BossHp -= MyAttack;
			BossHpLabel.setString("BossHP: " + BossHp + "/" + BossMaxHp);
			QLabel.setString("敵に　" + MyAttack + "　の　ダメージ！");
			submachinegun.play();


			System.out.println(BossHp);
		}

		// 下　あきらめる
		if (key.getVirtualKey() == RWTVirtualController.DOWN && Turn ==0 && ending ==0) {
			BGM3D.playBGM(muon);
			game.ending();
		}

		// 右　回復
		if (key.getVirtualKey() == RWTVirtualController.RIGHT && Turn ==0 && ending ==0) {
			Item -= 1;
			Turn = 0;
			MyHp += 250;
			if (MyHp > 500) {
				MyHp = 500;
			}
			QLabel.setString("HPが回復した");
			MyHpLabel.setString("HP: " + MyHp + "/" + MaxMyHp);
			System.out.println(MyHp + " " + Item);
			kaihuku.play();
		}

		// 左　自爆
		if (key.getVirtualKey() == RWTVirtualController.LEFT && Turn ==0 && ending ==0) {
			Zanki -= 1;
			BossHp -= 400;
			MyHp = MaxMyHp;
			ZankiLabel.setString("残機 × " + Zanki);
			BossHpLabel.setString("BossHP: " + BossHp + "/" + BossMaxHp);
			QLabel.setString("自爆に敵を巻き込んだ　" + 400 + "　のダメージ！");
			bomb.play();
			System.out.println(BossHp + " " + Zanki);
		}
		//エンディングに飛ばす
		if(BossHp <= 0 && ending ==1) {//勝った場合
			trumpet.play();
			BGM3D.playBGM(muon);
			game.ending();
		}
		if(Zanki <= -1 && shibou == 1) {//負けた場合
			BGM3D.playBGM(muon);
			game.ending();
		}

		//敵の攻撃
		if(Turn == 1 && BossHp > 0) {

			MyHp -= BossAttack;
			if(MyHp <= 0) {//残機をへらす
				Zanki --;
				MyHp = MaxMyHp;
			}

			ZankiLabel.setString("残機 × " + Zanki);
			MyHpLabel.setString("HP: " + MyHp + "/" + MaxMyHp);
			QLabel.setString(" 敵から　" + BossAttack + "　の　ダメージ！");
			attack.play();
		}

		if(Turn < 2) {
			Turn += 1;
		} else {
			Turn = 0;
			QLabel.setString("どうする？");
		}
		if(BossHp < 300 && angry == 0) {//HP一定以下で敵の攻撃力を上げる
			BossAttack = 150;
			QLabel.setString("敵の攻撃力が上がった");
			Turn = 2;
			angry = 1;
		}
		if(BossHp <= 0) {//撃破時の文字表示
			QLabel.setString("敵を撃破した");

			ending = 1;
		}
		if(Zanki <= -1) {
			QLabel.setString("やられてしまった");
			tin.play();
			shibou = 1;
		}
		repaint();
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
