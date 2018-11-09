package template.shooting2D;
import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.game2D.Velocity2D;
import framework.model3D.Universe;


public class EnemyScript extends Sprite {
	// 衝突判定用のBoundingSphereの半径
	public double collisionRadius =1.0;

	//敵の体力
	public int enemyHP;

	//敵の速度
	public double enemySpeed;

	//進む方向(角度)
	public double angle;
	ArrayList<MyBullet> myBulletList;

	Universe universe;

	MyShipScript myShipScript;
	int rangeWidth = TemplateShooting2D.RANGE;
	int rangeHeight = TemplateShooting2D.RANGE;
	//敵のインスタンスを生成する際に、ステータスを設定する
	//プレイヤー,敵の画像,敵の体力,速度,角度
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
			System.out.println("プレイヤーと衝突した！！");
		}
		for(int i=0;i<myBulletList.size();i++) {
			if(checkCollision(myBulletList.get(i))) {
				enemyHP--;
				universe.displace(myBulletList.get(i));
				myBulletList.remove(myBulletList.get(i));
				System.out.println("弾が衝突した！！");
			}
		}
		if(enemyHP<=0) {
			universe.displace(this);
			setPosition(0,-100);
			TemplateShooting2D.EnemyShootingDownNumber++;
		}
	}


}
