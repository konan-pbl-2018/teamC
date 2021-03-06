package template.shooting2D;

import framework.game2D.Sprite;
import framework.model3D.Universe;

public class MyShipBullet extends Sprite {

	Universe uni;

	public MyShipBullet(String imageFile) {
		super(imageFile);

		// TODO 自動生成されたコンストラクター・スタブ
	}
	public void move() {
		if(!isInScreen(TemplateShooting2D.RANGE,TemplateShooting2D.RANGE)) {

		}
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
