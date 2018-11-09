package template.shooting2D;

import java.util.ArrayList;

import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Ground2D;
import framework.gameMain.SimpleShootingGame;
import framework.model3D.Universe;

public class TemplateShooting2D extends SimpleShootingGame {

	private ArrayList<MyBullet> myBulletList = new ArrayList<MyBullet>();
	private MyShipScript myShipScript;
	private EnemyScript enemyScript;


//
	private Ground2D stage;


	public static int EnemyShootingDownNumber = 0;
	public static long PlayTimeCount = 0;

	// あとで設計変更
	// Enemyクラスでこの値を使いたいため。
	public static final int RANGE = 30;

	private Back background;

	@Override
	public void init(Universe universe) {

		// /////////////////////////////////////////////////////////
		//
		// 各登場物の初期化
		//
		// ////////////////////////////////////////////////////////


		myShipScript = new MyShipScript(universe, "data\\\\sozai\\\\自機の大きさ25%.png", myBulletList, 5, 10);
		myShipScript.setPosition(0.0, 0.0);
		universe.place(myShipScript);

		enemyScript = new EnemyScript(universe, myShipScript,myBulletList, "data\\images\\Enemy.gif", 10, 0, 10, -5, 0);
		universe.place(enemyScript);


		stage = new Ground2D(null, null, windowSizeWidth,
				windowSizeHeight);
		universe.place(stage);

		background = new Back("data\\sozai\\back ground.png", RANGE / 2);
		universe.place(background);

		// 表示範囲を決める（左上を原点としてその原点から幅、高さを計算する）
		setViewRange(RANGE, RANGE);
	}

	@Override
	public RWTFrame3D createFrame3D() {
		// TODO Auto-generated method stub
		RWTFrame3D f = new RWTFrame3D();
		f.setSize(960, 960);
		// f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.setTitle("Template for Shooting 2DGame");
		return f;
	}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {

		//ゲームを始めてからの経過時間のカウント
		PlayTimeCount += interval;
		//		if(PlayTimeCount / 1000 >= 10) {
		//			System.out.println("十秒");
		//		}

		//ゲーム画面の背景を時間経過によって動かす
		background.display();

		//自機
		myShipScript.move(virtualController);


		//敵
		enemyScript.motion(interval);
		enemyScript.oneShot(30, 10);
		enemyScript.everyDirection(31, 10, 6);

		//弾
		for(int i=0;i<myBulletList.size();i++) {
			myBulletList.get(i).move(interval);
		}


	}


	/**
	 * ゲームのメイン
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		TemplateShooting2D game = new TemplateShooting2D();
		game.setFramePolicy(5, 33, false);
		game.start();
	}


}
