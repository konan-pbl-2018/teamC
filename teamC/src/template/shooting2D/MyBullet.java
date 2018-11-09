package template.shooting2D;

import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.game2D.Velocity2D;
import framework.model3D.Universe;

public class MyBullet extends Sprite  {
	Universe uni;
	public boolean onDisplay=false;
	public float speed;
	ArrayList<MyBullet> myBulletList;

	public MyBullet(Universe u,String imageFile,ArrayList<MyBullet> mb,float _s) {
		super(imageFile);
		uni=u;
		speed=_s;
		myBulletList=mb;
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public void move(long interval) {

		if(!isInScreen((int)(TemplateShooting2D.RANGE*1.1),TemplateShooting2D.RANGE)) {
			onDisplay=false;
			uni.displace(this);
			myBulletList.remove(this);
		}
		if(!onDisplay)return;
		Velocity2D vel=this.getVelocity();
		vel.set(speed,0);
		setVelocity(vel);
		super.motion(interval);
	}


	/**
	 * widthとheightで表されるウィンドウサイズ内に存在するかを判定する
	 *
	 * @param width
	 *            --- ウィンドウの幅
	 * @param height
	 *            --- ウィンドウの高さ
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

}
