package template.shooting2D;
import framework.game2D.Sprite;
import framework.game2D.Velocity2D;


public class EnemyScript extends Sprite {
	// 衝突判定用のBoundingSphereの半径
	public double collisionRadius =1.0;

	//敵の体力
	public int enemyHP;

	//敵の速度
	public double enemySpeed;

	//進む方向(角度)
	public double angle;

	MyShipScript myShipScript;
	int rangeWidth = TemplateShooting2D.RANGE;
	int rangeHeight = TemplateShooting2D.RANGE;
	//敵のインスタンスを生成する際に、ステータスを設定する
	//敵の画像,敵の体力,速度,角度
	public EnemyScript(String imageFile,int _enemyHP,double _x,double _y, double _speed,double _angle) {
		super(imageFile);
		enemyHP=_enemyHP;
		setPosition(_x,_y);
		enemySpeed=_speed;
		angle=_angle;
	}
	public void motion(long interval) {
		Velocity2D vel=this.getVelocity();
		vel.set(Math.cos(angle)*enemySpeed,Math.sin(angle)*enemySpeed);
		setVelocity(vel);
		super.motion(interval);

	}
	public void destroy() {
		if(enemyHP<=0) {
			TemplateShooting2D.EnemyShootingDownNumber++;
		}
	}


}
