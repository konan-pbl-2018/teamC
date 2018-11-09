package template.shooting2D;

import java.util.ArrayList;

import framework.RWT.RWTVirtualController;
import framework.game2D.Sprite;
import framework.model3D.Universe;

public class MyShipScript extends Sprite {
	public int myShipHP;
	public double speed;
	public int playerShift = 0;//�v���C���[0���v���C���[�P�����w��
	int countFrame = 0;//1�t���[�����Ƃɑ�������
	//�C���X�^���X�������ɏ����l��ݒ�
	ArrayList<MyBullet> myBulletList;
	Universe universe;
	//
	public MyShipScript(Universe u,String string, ArrayList<MyBullet> mb, int _hp, double _speed) {
		super(string);
		super.setCollisionRadius(0.5);
		myShipHP = _hp;
		speed = _speed;
		myBulletList = mb;
		universe =u;
	}

	//���@�𑀍삷��X�N���v�g
	void move(RWTVirtualController virtualController) {
		countFrame++;
		shot(virtualController);
		collision();
		//boolean pushShift=RWTVirtualController.;
		// �L�[����ɂ�鎩�@�̃A�N�V��������
		// ��
		if (virtualController.isKeyDown(playerShift, RWTVirtualController.LEFT)) {
			moveLeft(speed);
		}
		// �E
		if (virtualController.isKeyDown(playerShift, RWTVirtualController.RIGHT)) {
			moveRight(speed);
		}
		// ��
		if (virtualController.isKeyDown(playerShift, RWTVirtualController.UP)) {
			moveUp(speed);
		}
		// ��
		if (virtualController.isKeyDown(playerShift, RWTVirtualController.DOWN)) {
			moveDown(speed);
		}
		if (!(isInScreen(TemplateShooting2D.RANGE, TemplateShooting2D.RANGE))) {
			if (getPosition().getX() >= TemplateShooting2D.RANGE / 2) {
				setPosition(TemplateShooting2D.RANGE / 2, getPosition().getY());
			}
			if (getPosition().getX() <= -1.0 * TemplateShooting2D.RANGE / 2) {
				setPosition(-1.0 * TemplateShooting2D.RANGE / 2, getPosition().getY());
			}
			if (getPosition().getY() >= TemplateShooting2D.RANGE / 2) {
				setPosition(getPosition().getX(), TemplateShooting2D.RANGE / 2);
			}
			if (getPosition().getY() <= -1.0 * TemplateShooting2D.RANGE / 2) {
				setPosition(getPosition().getX(), -1.0 * TemplateShooting2D.RANGE / 2);
			}

		}

	}

	public void shot(RWTVirtualController virtualController) {
		if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_B)) {
			if (countFrame % 5 == 0) {


				MyBullet bullet=new MyBullet(universe,"data\\\\sozai\\\\�G�̒e25%.png",myBulletList,50,0,true);
				universe.place(bullet);
				bullet.setPosition(getPosition().getX(),getPosition().getY());
				bullet.onDisplay=true;
				this.myBulletList.add(bullet);


			}
		}
	}
	public void collision() {

		for(int i=0;i<myBulletList.size();i++) {
			if(checkCollision(myBulletList.get(i))&&!myBulletList.get(i).shipToEnemy) {

				universe.displace(myBulletList.get(i));
				myBulletList.remove(myBulletList.get(i));
				System.out.println("�e���Փ˂����I�I");
			}
		}
	}

	public void hit() {

	}

	// ////////////////////////////////////////////////////
	//
	// �v���C���[���E�B���h�E���ɂ��邩�ǂ����̃��\�b�h
	//
	// ///////////////////////////////////////////////////
	/**
	 * width��height�Ō��߂�ꂽ�E�B���h�E�̕��̒��Ƀv���C���[�����邩�ǂ�����Ԃ�
	 *
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean isInScreen(int width, int height) {
		if (this.getPosition().getX() < width / 2.0
				&& this.getPosition().getX() > -1.0 * width / 2.0) {
			if (this.getPosition().getY() < height / 2.0
					&& this.getPosition().getY() > -1.0 * height / 2.0) {
				return true;
			}
		}
		return false;
	}

	public boolean gameover() {
		return true;

	}
}
