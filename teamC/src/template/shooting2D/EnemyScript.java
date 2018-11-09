package template.shooting2D;
import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.game2D.Velocity2D;
import framework.model3D.Universe;


public class EnemyScript extends Sprite {
	// �Փ˔���p��BoundingSphere�̔��a
	public double collisionRadius =1.0;

	//�G�̗̑�
	public int enemyHP;

	//�G�̑��x
	public double enemySpeed;

	//�i�ޕ���(�p�x)
	public double angle;
	ArrayList<MyBullet> myBulletList;

	Universe universe;

	MyShipScript myShipScript;
	int rangeWidth = TemplateShooting2D.RANGE;
	int rangeHeight = TemplateShooting2D.RANGE;
	//�G�̃C���X�^���X�𐶐�����ۂɁA�X�e�[�^�X��ݒ肷��
	//�v���C���[,�G�̉摜,�G�̗̑�,���x,�p�x
	public EnemyScript(Universe u,MyShipScript ms,ArrayList<MyBullet> mb, String imageFile,int _enemyHP,double _x,double _y, double _speed,double _angle) {
		super(imageFile);
		enemyHP=_enemyHP;
		setPosition(_x,_y);
		enemySpeed=_speed;
		angle=_angle;
		myShipScript=ms;
		universe=u;
		myBulletList=mb;
	}
	public void motion(long interval) {
		collision();
		Velocity2D vel=this.getVelocity();
		vel.set(Math.cos(angle)*enemySpeed,Math.sin(angle)*enemySpeed);
		setVelocity(vel);
		super.motion(interval);

	}
	public void collision() {
		if(checkCollision(myShipScript)) {
			enemyHP--;
			System.out.println("�v���C���[�ƏՓ˂����I�I");
		}
		for(int i=0;i<myBulletList.size();i++) {
			if(checkCollision(myBulletList.get(i))) {
				enemyHP--;
				universe.displace(myBulletList.get(i));
				myBulletList.remove(myBulletList.get(i));
				System.out.println("�e���Փ˂����I�I");
			}
		}
		if(enemyHP<=0) {
			universe.displace(this);
			setPosition(0,-100);
			TemplateShooting2D.EnemyShootingDownNumber++;
		}
	}


}
