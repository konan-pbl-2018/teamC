package template.shooting2D;

import java.awt.Event;
import java.util.ArrayList;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Ground2D;
import framework.gameMain.IGameState;
import framework.gameMain.SimpleShootingGame;
import framework.model3D.Universe;

public class TemplateShooting2DMultiStates extends SimpleShootingGame {

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

	private IGameState initialGameState = null;
	private IGameState finalGameState = null;
	private IGameState rpgGameState = null;
//	private IGameState shootingGameState = null;/////////////////////////////////////////ここから下が
	//変更点です。
	private int time;
	private RWTContainer container;///////////////////////////////////////////////////////

	public TemplateShooting2DMultiStates() {
		super();
		initialGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				TemplateShooting2DMultiStates.this.frame = frame;
				container = new StartContainer(TemplateShooting2DMultiStates.this);
				changeContainer(container);
			}

			@Override
			public boolean useTimer() {
				return false;
			}

			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
		finalGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				TemplateShooting2DMultiStates.this.frame = frame;
				container = new EndingContainer(TemplateShooting2DMultiStates.this);
				changeContainer(container);
			}

			@Override
			public boolean useTimer() {
				return false;
			}

			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
		rpgGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				TemplateShooting2DMultiStates.this.frame = frame;
				container = new RPGContainer(TemplateShooting2DMultiStates.this);
				changeContainer(container);
			}

			@Override
			public boolean useTimer() {
				return false;
			}

			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
//		shootingGameState = new IGameState() {
//			private int time;
//
//			@Override
//			public void init(RWTFrame3D frame) {
//				TemplateShooting2DMultiStates.this.frame = frame;
//				container = new ShootingContainer(TemplateShooting2DMultiStates.this);
//				changeContainer(container);
//			}
//
//			@Override
//			public boolean useTimer() {
//				return true;
//			}
//
//			@Override
//			public void update(RWTVirtualController virtualController, long interval) {
//				System.out.println("a");
//				time += interval;
//				((ShootingContainer) container).setStartLabelText("" + time);
//			}
//		};
		setCurrentGameState(initialGameState);
	}

	protected RWTContainer createRWTContainer() {
		container = new ShootingContainer(TemplateShooting2DMultiStates.this);
		return container;
	}

	public void restart() {
		stop();
		setCurrentGameState(initialGameState);
		start();
	}

	public void play() {
		stop();
		setCurrentGameState(this);
		start();
	}

	public void ending() {
		stop();
		setCurrentGameState(finalGameState);
		start();
	}

	public void rpg() {
		stop();
		setCurrentGameState(rpgGameState);
		start();
	}

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
		f.setSize(800, 800);
		// f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.setTitle("Template for Shooting 2DGame");
		return f;
	}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
		System.out.println("a");
		time += interval;
		((ShootingContainer) container).setStartLabelText("" + time);

		if (virtualController.isKeyDown(Event.ENTER)) {
			ending();
		}


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
		enemyScript.everyDirection(31, 10, 5);

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
		TemplateShooting2DMultiStates game = new TemplateShooting2DMultiStates();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

}
