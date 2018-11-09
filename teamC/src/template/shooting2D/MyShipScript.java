package template.shooting2D;

import java.util.ArrayList;

import framework.RWT.RWTVirtualController;
import framework.game2D.Sprite;
import framework.model3D.Universe;

public class MyShipScript extends Sprite {
	public int myShipHP;
	public double speed;
	public int playerShift = 0;//プレイヤー0かプレイヤー１かを指定
	int countFrame = 0;//1フレームごとに増加する
	//インスタンス生成時に初期値を設定
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

	//自機を操作するスクリプト
	void move(RWTVirtualController virtualController) {
		countFrame++;
		shot(virtualController);
		//boolean pushShift=RWTVirtualController.;
		// キー操作による自機のアクション処理
		// 左
		if (virtualController.isKeyDown(playerShift, RWTVirtualController.LEFT)) {
			moveLeft(speed);
		}
		// 右
		if (virtualController.isKeyDown(playerShift, RWTVirtualController.RIGHT)) {
			moveRight(speed);
		}
		// 上
		if (virtualController.isKeyDown(playerShift, RWTVirtualController.UP)) {
			moveUp(speed);
		}
		// 下
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
				System.out.println("発射！！");
				/*
				for(int i=0;i<myBullet.size();i++) {
					MyBullet mb=myBullet.get(i);
X
					if (mb.onDisplay)
						continue;
					else {
						mb.setPosition(getPosition().getX(),getPosition().getY());
						mb.onDisplay=true;
						break;
					}
				}
				*/
				MyBullet bullet=new MyBullet(universe,"data\\\\sozai\\\\敵の弾切り取り消したやつ.png",myBulletList,20);
				universe.place(bullet);
				bullet.setPosition(getPosition().getX(),getPosition().getY());
				bullet.onDisplay=true;
				this.myBulletList.add(bullet);


			}
		}
	}

	public void hit() {

	}

	// ////////////////////////////////////////////////////
	//
	// プレイヤーがウィンドウ内にいるかどうかのメソッド
	//
	// ///////////////////////////////////////////////////
	/**
	 * widthとheightで決められたウィンドウの幅の中にプレイヤーがいるかどうかを返す
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
