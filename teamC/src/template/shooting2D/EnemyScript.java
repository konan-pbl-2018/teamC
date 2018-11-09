package template.shooting2D;
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
	Universe uni;

	MyShipScript myShipScript;
	int rangeWidth = TemplateShooting2D.RANGE;
	int rangeHeight = TemplateShooting2D.RANGE;
	//�G�̃C���X�^���X�𐶐�����ۂɁA�X�e�[�^�X��ݒ肷��
	//�v���C���[,�G�̉摜,�G�̗̑�,���x,�p�x
	public EnemyScript(Universe u,MyShipScript ms, String imageFile,int _enemyHP,double _x,double _y, double _speed,double _angle) {
		super(imageFile);
		enemyHP=_enemyHP;
		setPosition(_x,_y);
		enemySpeed=_speed;
		angle=_angle;
		myShipScript=ms;
		uni=u;
	}
	public void motion(long interval) {
		Velocity2D vel=this.getVelocity();
		vel.set(Math.cos(angle)*enemySpeed,Math.sin(angle)*enemySpeed);
		setVelocity(vel);
		super.motion(interval);

	}
	public void collision() {
		if(checkCollision(myShipScript)) {
			System.out.println("�v���C���[�ƏՓ˂����I�I");
		}
		if(enemyHP<=0) {
			uni.displace(this);
			TemplateShooting2D.EnemyShootingDownNumber++;
		}
	}


}
