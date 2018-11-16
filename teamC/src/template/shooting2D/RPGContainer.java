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
	int ending = 0;//�G���f�B���O�̃t���O
	int angry = 0;//�{���Ԃ̃t���O
	int shibou = 0;//���S�̃t���O
	int kousan = 0;
	int BossHp = 2500;
	int BossMaxHp = BossHp;
	int BossAttack = 70;
	int Zanki = 5;//�c�@
	int MyHp = 400;//���C�t
	int MaxMyHp = MyHp;
	int MyAttack = 50;//�U����
	int Item = 5;//�񕜉�

	Sound3D battle = BGM3D.registerBGM("data\\sozai\\BGM\\battle.wav");
	Sound3D muon = BGM3D.registerBGM("data\\sozai\\BGM\\muon.wav");
	Sound3D attack = new Sound3D("data\\sozai\\BGM\\punch-high1.wav");
	Sound3D submachinegun = new Sound3D("data\\sozai\\BGM\\submachinegun1.wav");
	Sound3D kaihuku = new Sound3D("data\\sozai\\BGM\\magic-status-cure1.wav");
	Sound3D bomb = new Sound3D("data\\sozai\\BGM\\bomb1.wav");
	Sound3D monstergrow = new Sound3D("data\\sozai\\BGM\\monster-growl1.wav");
	Sound3D tin = new Sound3D("data\\sozai\\BGM\\tin1.wav");
	Sound3D trumpet = new Sound3D("data\\sozai\\BGM\\trumpet1.wav");

	RWTLabel KaihukuLabel = new RWTLabel();
	RWTLabel ZankiLabel = new RWTLabel();
	RWTLabel MyHpLabel = new RWTLabel();
	RWTLabel QLabel = new RWTLabel();
	RWTLabel BossHpLabel = new RWTLabel();

	@Override
	public void build(GraphicsConfiguration gc) {//��ʕ\��

		BGM3D.playBGM(battle);
		monstergrow.play();

		RWTImage BackImage = new RWTImage("data\\sozai\\RPGBack.png");
		BackImage.setRelativePosition(-1f, 0f);
		addWidget(BackImage);

		RWTImage BackImage2 = new RWTImage("data\\sozai\\blackback.png");
		BackImage2.setRelativePosition(0.02f, 0.7f);
		addWidget(BackImage2);
		repaint();

		RWTLabel AttackLabel = new RWTLabel();
		AttackLabel.setString("W:�U��");//RWTLabel.NEW_PARAGRAPH
		AttackLabel.setRelativePosition(0.05f, 0.85f);
		AttackLabel.setColor(Color.WHITE);
		Font f1 = new Font("", Font.PLAIN, 30);
		AttackLabel.setFont(f1);
		addWidget(AttackLabel);

		KaihukuLabel.setString("D:�� �~ " + Item);
		KaihukuLabel.setRelativePosition(0.35f, 0.85f);
		KaihukuLabel.setColor(Color.WHITE);
		KaihukuLabel.setFont(f1);
		addWidget(KaihukuLabel);

		RWTLabel ZibakuLabel = new RWTLabel();
		ZibakuLabel.setString("A:����");
		ZibakuLabel.setRelativePosition(0.05f, 0.95f);
		ZibakuLabel.setColor(Color.WHITE);
		ZibakuLabel.setFont(f1);
		addWidget(ZibakuLabel);

		RWTLabel EscapeLabel = new RWTLabel();
		EscapeLabel.setString("S:���߂�");
		EscapeLabel.setRelativePosition(0.35f, 0.95f);
		EscapeLabel.setColor(Color.WHITE);
		EscapeLabel.setFont(f1);
		addWidget(EscapeLabel);

		ZankiLabel.setString("�c�@ �~ " + Zanki);
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

		QLabel.setString("�G�̃{�X�������ꂽ");
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

		RWTImage MyImage = new RWTImage("data\\sozai\\���@�ŏI�ł�.png");
		MyImage.setRelativePosition(0f, 0.36f);
		addWidget(MyImage);

		RWTImage BossImage = new RWTImage("data\\images\\boss.png");
		BossImage.setRelativePosition(0.65f, 0.05f);
		addWidget(BossImage);

		repaint();

	}

	public void update() {
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {

		if (kousan == 1) {//�~�Q�����Ƃ��G���f�B���O�ɔ�΂�
			tin.play();
			game.ending();
		}

		// ��@�키
		if (key.getVirtualKey() == RWTVirtualController.UP && Turn == 0 && ending == 0) {
			repaint();
			BossHp -= MyAttack;
			BossHpLabel.setString("BossHP: " + BossHp + "/" + BossMaxHp);
			QLabel.setString("�G�Ɂ@" + MyAttack + "�@�́@�_���[�W�I");
			submachinegun.play();
			System.out.println(BossHp);
		}

		// ���@������߂�
		if (key.getVirtualKey() == RWTVirtualController.DOWN && Turn == 0 && ending == 0) {
			BGM3D.playBGM(muon);
			QLabel.setString("������߂�");
			kousan = 1;
		}

		// �E�@��
		if (key.getVirtualKey() == RWTVirtualController.RIGHT && Turn == 0 && ending == 0) {
			if (Item > 0) {
				Item -= 1;
				Turn = 0;
				MyHp += 300;

				if (MyHp > MaxMyHp) {
					MyHp = MaxMyHp;
				}
				QLabel.setString("HP���񕜂���");
				MyHpLabel.setString("HP: " + MyHp + "/" + MaxMyHp);
				KaihukuLabel.setString("D:�� �~ " + Item);
				System.out.println(MyHp + " " + Item);
				kaihuku.play();
			}else {
				Turn = 2;
			}
		}

		// ���@����
		if (key.getVirtualKey() == RWTVirtualController.LEFT && Turn == 0 && ending == 0) {
			Zanki -= 1;
			BossHp -= MaxMyHp;

			if (Zanki >= 0) {
				MyHp = MaxMyHp;
			} else if (Zanki == 0) {
				MyHp = 0;
			}

			ZankiLabel.setString("�c�@ �~ " + Zanki);
			BossHpLabel.setString("BossHP: " + BossHp + "/" + BossMaxHp);
			MyHpLabel.setString("HP: " + MyHp + "/" + MaxMyHp);
			QLabel.setString("�����ɓG���������񂾁@" + MaxMyHp + "�@�̃_���[�W�I");
			bomb.play();
			System.out.println(BossHp + " " + Zanki);
		}

		//�G���f�B���O�ɔ�΂�
		if (BossHp <= 0 && ending == 1) {//�������ꍇ
			trumpet.play();
			game.ending();
		}
		if (Zanki <= -1 && shibou == 1) {//�������ꍇ
			tin.play();
			game.ending();
		}

		//�G�̍U��
		if (Turn == 1 && BossHp > 0) {

			MyHp -= BossAttack;
			if (MyHp < 0 && Zanki > 0) {//�c�@���ւ炷��HP���ő�l�ɂ���
				MyHp = MaxMyHp;
				Zanki--;
			} else if (MyHp < 0 && Zanki == 0) {//�c�@���Ȃ��ꍇ��HP��0�ɂ���
				MyHp = 0;
				Zanki--;
			}
			ZankiLabel.setString("�c�@ �~ " + Zanki);
			MyHpLabel.setString("HP: " + MyHp + "/" + MaxMyHp);
			QLabel.setString(" �G����@" + BossAttack + "�@�́@�_���[�W�I");
			attack.play();
		}

		if (Turn < 2) {
			Turn += 1;
		} else {
			Turn = 0;
			QLabel.setString("�ǂ�����H");
		}

		if (BossHp < 300 && angry == 0) {//HP���ȉ��œG�̍U���͂��グ��
			BossAttack = 150;
			QLabel.setString("�G�̍U���͂��オ����");
			Turn = 2;
			angry = 1;
		}

		if (BossHp <= 0) {//���j���̏���
			BossHp = 0;
			BossHpLabel.setString("BossHP: " + BossHp + "/" + BossMaxHp);
			QLabel.setString("�G�����j����");
			BGM3D.playBGM(muon);
			ending = 1;
		}

		if (Zanki <= -1) {//�팂�j���̏���
			QLabel.setString("����Ă��܂���");
			BGM3D.playBGM(muon);
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
