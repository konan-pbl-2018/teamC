package template.shooting2D;

import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.game2D.Velocity2D;
import framework.model3D.Universe;

public class EnemyScript extends Sprite {
	// �Փ˔���p��BoundingSphere�̔��a
	public double collisionRadius = 1.0;

	//�G�̗̑�
	public int enemyHP;

	//�G�̑��x
	public double enemySpeed;

	//�i�ޕ���(�p�x)
	public double angle;
	private boolean isDestroy;

	ArrayList<MyBullet> myBulletList;

	Universe universe;

	MyShipScript myShipScript;
	String itemName;
	int countFrame = 0;
	int rangeWidth = TemplateShooting2D.RANGE;
	int rangeHeight = TemplateShooting2D.RANGE;

	//�G�̃C���X�^���X�𐶐�����ۂɁA�X�e�[�^�X��ݒ肷��
	//�v���C���[,�G�̉摜,�G�̗̑�,���x,�p�x
	public EnemyScript(Universe u, MyShipScript ms, ArrayList<MyBullet> mb, String imageFile, int _enemyHP,  double _speed, double _angle,String itemname) {
		super(imageFile);
		enemyHP = _enemyHP;

		enemySpeed = _speed;
		angle = _angle;
		myShipScript = ms;
		universe = u;
		myBulletList = mb;
		itemName=itemname;
		isDestroy=false;
	}

	public void motion(long interval) {
		if(isDestroy)return;
		double rad=angle/180*Math.PI;
		collision();
		Velocity2D vel = this.getVelocity();
		vel.set(Math.cos(rad) * enemySpeed, Math.sin(rad) * enemySpeed);
		setVelocity(vel);
		super.motion(interval);
		countFrame++;

	}

	public void collision() {
		if (checkCollision(myShipScript)) {
			universe.displace(this);
			isDestroy=true;
			setPosition(0, -100);
			RPGContainer.Zanki-=1;
			System.out.println("�v���C���[�ƏՓ˂����I�I");
		}
		for (int i = 0; i < myBulletList.size(); i++) {
			if (checkCollision(myBulletList.get(i)) && myBulletList.get(i).shipToEnemy) {
				enemyHP--;
				universe.displace(myBulletList.get(i));
				myBulletList.remove(myBulletList.get(i));
				System.out.println("�e���Փ˂����I�I");
			}
		}
		if (enemyHP <= 0) {

			TemplateShooting2DMultiStates.EnemyShootingDownNumber++;
			Item item=new Item(universe,myShipScript,itemName);
			TemplateShooting2DMultiStates.itemList.add(item);
			item.setPosition(getPosition().getX(), getPosition().getY());
			universe.place(item);
			universe.displace(this);
			isDestroy=true;
			setPosition(0, -100);
		}
	}

	public void oneShot(int intervalFrame, float speed) {
		if (countFrame % intervalFrame == 0) {

			MyBullet bullet = new MyBullet(universe, "data\\\\sozai\\\\�G�̒e�؂�����������.png", myBulletList, -speed, 0,
					false);
			universe.place(bullet);
			bullet.setPosition(getPosition().getX(), getPosition().getY());
			bullet.onDisplay = true;
			this.myBulletList.add(bullet);
		}

	}

	public void everyDirection(int intervalFrame, float speed, int num) {
		if (countFrame % intervalFrame == 0) {
			for (float i = 0; i < num; i++) {
				MyBullet bullet = new MyBullet(universe, "data\\\\sozai\\\\�e�ŏI.png", myBulletList, -speed,2*(float)Math.PI * i / num ,false);
				//((Object3D) bullet.getBody()).rotate(0, 0, 1, Math.PI * i / num);
				universe.place(bullet);
				bullet.setPosition(getPosition().getX(), getPosition().getY());
				bullet.onDisplay = true;
				this.myBulletList.add(bullet);
			}
		}

	}

}
