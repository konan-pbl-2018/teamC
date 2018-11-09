package template.shooting2D;

import java.util.ArrayList;

import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Ground2D;
import framework.game2D.Sprite;
import framework.gameMain.SimpleShootingGame;
import framework.model3D.Universe;

public class TemplateShooting2D extends SimpleShootingGame {
	private MyShipSprite myShipSprite;

	//private MyShipBullet myShipBullet;
	private ArrayList<MyShipBullet> myShipBulletList = new ArrayList<MyShipBullet>();
	private ArrayList<MyShipBullet> myShipBulletFromMyShip = new ArrayList<MyShipBullet>();

	private ArrayList<MyBullet> myBulletList = new ArrayList<MyBullet>();
	private MyShipScript myShipScript;
	private EnemyScript enemyScript;

	private EnemySprite enemySprite;
	private ArrayList<EnemyBullet> enemyBulletList = new ArrayList<EnemyBullet>();
	private ArrayList<EnemyBullet> enemyBulletFromEnemy = new ArrayList<EnemyBullet>();

	private Ground2D stage;

	private long lastMyShipBulletShootTime = 0;
	private long lastMyShipBulletShootDanamakuTime = 0;
	private long lastEnemyShootTime = 0;

	public static int EnemyShootingDownNumber = 0;
	public static long PlayTimeCount = 0;

	// あとで設計変更
	// Enemyクラスでこの値を使いたいため。
	public static final int RANGE = 30;
	private Sprite back;
	private Back background;

	@Override
	public void init(Universe universe) {

		// /////////////////////////////////////////////////////////
		//
		// 各登場物の初期化
		//
		// ////////////////////////////////////////////////////////
		//		myShipSprite = new MyShipSprite("data\\images\\MyShip.gif");
		//		myShipSprite.setPosition(0.0, -10.0);
		//		universe.place(myShipSprite);


		myShipScript = new MyShipScript(universe, "data\\\\images\\\\MyShip.gif", myBulletList, 5, 10);
		myShipScript.setPosition(0.0, 0.0);
		universe.place(myShipScript);

		enemyScript = new EnemyScript(universe, myShipScript,myBulletList, "data\\images\\Enemy.gif", 20, 0, 10, 1, 0);
		universe.place(enemyScript);

		enemySprite = new EnemySprite("data\\images\\Enemy.gif");
		enemySprite.setPosition(0.0, 10.0);
		enemySprite.setVelocity(5.0, 5.0);
		universe.place(enemySprite);

		stage = new Ground2D(null, null, windowSizeWidth,
				windowSizeHeight);
		universe.place(stage);
		//		back = new Back("data\\sozai\\back ground.png", RANGE / 2);
		//		universe.place(back);

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

		myShipScript.move(virtualController);
		enemyScript.motion(interval);
		for(int i=0;i<myBulletList.size();i++) {
			myBulletList.get(i).move(interval);
		}
		// /////////////////////////////////////////////////////////
		//
		// 各登場物のアクション
		//
		// ////////////////////////////////////////////////////////

		// 敵のアクション処理
		// 弾幕の発射
		if (System.currentTimeMillis() - lastEnemyShootTime > 1000) {
			enemyBulletFromEnemy = enemySprite.shootDanmaku();
			this.setEnemyBullet(enemyBulletFromEnemy);
			lastEnemyShootTime = System.currentTimeMillis();
		}

		// /////////////////////////////////////////////////////////
		//
		// 各登場物を動かす処理
		//
		// ////////////////////////////////////////////////////////

		// プレイヤーの弾を動かす
		//		for (int i = 0; i < myShipBulletList.size(); i++) {
		//			MyShipBullet myShipBullet = myShipBulletList.get(i);
		//			myShipBullet.motion(interval);		// プレイヤーの弾の移動
		//			if (myShipBullet.isInScreen(viewRangeWidth, viewRangeHeight) == false) {
		//				// プレイヤーの弾を消す
		//				universe.displace(myShipBullet);
		//				myShipBulletList.remove(i);
		//			}
		//		}

		// 敵（スプライト）を動かす
		enemySprite.motion(interval);

		// 敵の弾を動かす。同時にウィンドウ外に出てしまったかどうかを判定し、出てしまったらウインドウから弾を消す。
		for (int i = 0; i < enemyBulletList.size(); i++) {
			EnemyBullet enemyBullet = enemyBulletList.get(i);
			enemyBullet.motion(interval); // 敵の弾の移動
			if (enemyBullet.isInScreen(viewRangeWidth, viewRangeHeight) == false) {
				// 敵の弾を消す
				universe.displace(enemyBullet);
				enemyBulletList.remove(i);
			}
		}

		// /////////////////////////////////////////////////////////
		//
		// 各登場物を動かした後の処理
		//
		// ////////////////////////////////////////////////////////

		// プレイヤーと〇〇の衝突判定
		// 衝突判定（プレイヤーと敵の弾）
		//		for (int i = 0; i < enemyBulletList.size(); i++) {
		//			EnemyBullet enemyBullet = enemyBulletList.get(i);
		//			if (myShipSprite.checkCollision(enemyBullet)) {
		//				System.out.println("敵の弾" + i + "と衝突した！");
		//			}
		//		}
		//
		//		// 衝突判定（プレイヤーの弾と敵の弾）
		//		for (int i = 0; i < myShipBulletList.size(); i++) {
		//			MyShipBullet myShipBullet = myShipBulletList.get(i);
		//			for (int j = 0; j < enemyBulletList.size(); j++) {
		//				EnemyBullet enemyBullet = enemyBulletList.get(j);
		//				if (myShipBullet.checkCollision(enemyBullet)) {
		//					// 敵の弾を消す
		//					System.out.println("プレイヤーの弾" + i + "が敵の弾" + j + "と衝突した！");
		//					universe.displace(enemyBullet);
		//					enemyBulletList.remove(j);
		//				}
		//			}
		//		}

		//		// 衝突判定（プレイヤーの弾と敵）
		//		for (int i = 0; i < myShipBulletList.size(); i++) {
		//			MyShipBullet myShipBullet = myShipBulletList.get(i);
		//			if (myShipBullet.checkCollision(enemySprite)) {
		//				System.out.println("プレイヤーの弾が敵に衝突した！");
		//				enemySprite.addEnemyHP(-10);
		//				System.out.println("敵のHP" + enemySprite.getEnemyHP());
		//
		//				if (enemySprite.shootDown()) {
		//					System.out.println("敵を倒した！");
		//				}
		//
		//			}
		//		}
		//
		//		// 衝突判定（プレイヤーと敵）
		//		if (myShipSprite.checkCollision(enemySprite)) {
		//			System.out.println("敵と衝突した！");
		//		}

	}

	/**
	 * 弾幕が入ったリスト（myShipBulletFromMyShip）をプレイヤーの弾のリストに設定する
	 *
	 * @param myShipBulletFromMyShip
	 */
	public void setMyShipBullet(ArrayList<MyShipBullet> myShipBulletFromMyShip) {
		for (int i = 0; i < myShipBulletFromMyShip.size(); i++) {
			universe.place(myShipBulletFromMyShip.get(i));
			this.myShipBulletList.add(myShipBulletFromMyShip.get(i));
		}
	}

	/**
	 * 弾幕が入ったリスト（enemyBulletListFromEnemy）を敵の弾のリストに設定する
	 *
	 * @param enemyBulletListFromEnemy
	 */
	public void setEnemyBullet(ArrayList<EnemyBullet> enemyBulletListFromEnemy) {
		for (int i = 0; i < enemyBulletListFromEnemy.size(); i++) {
			universe.place(enemyBulletListFromEnemy.get(i));
			this.enemyBulletList.add(enemyBulletListFromEnemy.get(i));
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
