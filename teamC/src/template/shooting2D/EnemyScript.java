package template.shooting2D;
import framework.game2D.Sprite;
import framework.game2D.Velocity2D;


public class EnemyScript extends Sprite {
	// �Փ˔���p��BoundingSphere�̔��a
	public double collisionRadius =1.0;

	//�G�̗̑�
	public int enemyHP;

	//�G�̑��x
	public double enemySpeed;

	//�i�ޕ���(�p�x)
	public double angle;
	int rangeWidth = TemplateShooting2D.RANGE;
	int rangeHeight = TemplateShooting2D.RANGE;
	//�G�̃C���X�^���X�𐶐�����ۂɁA�X�e�[�^�X��ݒ肷��
	//�G�̉摜,�G�̗̑�,���x,�p�x
	public EnemyScript(String imageFile,int _enemyHP,double _speed,double _angle) {
		super(imageFile);
		enemyHP=_enemyHP;
	}
	public void motion(long interval) {
		Velocity2D vel=this.getVelocity();
		vel.set(Math.cos(angle)*enemySpeed,Math.sin(angle)*enemySpeed);
		setVelocity(vel);
		super.motion(interval);

	}
	public void destroy() {
		if(enemyHP<=0) {

		}
	}


}
