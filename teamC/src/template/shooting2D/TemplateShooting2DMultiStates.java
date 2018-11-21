package template.shooting2D;

import java.awt.Event;
import java.util.ArrayList;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.audio.BGM3D;
import framework.audio.Sound3D;
import framework.game2D.Ground2D;
import framework.gameMain.IGameState;
import framework.gameMain.SimpleShootingGame;
import framework.model3D.Universe;

public class TemplateShooting2DMultiStates extends SimpleShootingGame {

	ArrayList<MyBullet> myBulletList = new ArrayList<MyBullet>();
	private MyShipScript myShipScript;
	private EnemyScript enemyScript;

	public static ArrayList<EnemyScript> enemyList = new ArrayList<EnemyScript>();
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	private Item item;
	private Ground2D stage;

	private int enemySetFrame = 0;
	public static int EnemyShootingDownNumber = 0;
	public static long PlayTimeCount = 0;

	// あとで設計変更
	// Enemyクラスでこの値を使いたいため。
	public static final int RANGE = 30;

	private Back background;

	private IGameState initialGameState = null;
	private IGameState finalGameState = null;
	private IGameState rpgGameState = null;
	private IGameState gameoverGameState = null;
	private RWTContainer container;
	private Sound3D shootingBGM = BGM3D.registerBGM("data\\sozai\\BGM\\shooting.wav");

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
		gameoverGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				TemplateShooting2DMultiStates.this.frame = frame;
				container = new GameOverContainer(TemplateShooting2DMultiStates.this);
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

	public void go() {
		stop();
		setCurrentGameState(gameoverGameState);
		start();
	}

	public void rpg() {
		stop();
		setCurrentGameState(rpgGameState);
		start();
	}

	@Override
	public void init(Universe universe) {
		BGM3D.playBGM(shootingBGM);

		// /////////////////////////////////////////////////////////
		//
		// 各登場物の初期化
		//
		// ////////////////////////////////////////////////////////

		myShipScript = new MyShipScript(universe, "data\\\\sozai\\\\自機の大きさ25%.png", myBulletList, 5, 10);
		myShipScript.setPosition(0.0, 0.0);
		universe.place(myShipScript);

		enemyScript = new EnemyScript(universe, myShipScript, myBulletList, "data\\images\\Enemy.gif", 10, -1, 0,
				"Attack");
		enemyScript.setPosition(10, 0);
		//universe.place(enemyScript);

		item = new Item(universe, myShipScript, "Attack");
		item.setPosition(4.0, 0.0);
		universe.place(item);

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

		//タイムとライフの表示
		((ShootingContainer) container).setStartLabelText("" + (60 - PlayTimeCount / 1000));

		//RPGへの移行
		if(PlayTimeCount / 1000 >= 60){
			rpg();
		}


		switch (enemySetFrame) {
		case 1*60:
			setEnemy(5, 0, 2, 10,-5,0, "Attack");
			break;
		case 3*60:
			setEnemy(10, 0, 2, 1,-5,0, "Attack");
			break;
		case 6*60:
			for(int i=0;i<5;i++)setEnemy(16, 10-i*2, 2, 1,-5,0, "Attack");
			break;
		case 10*60:
			setEnemy(10, 0, 2, 10,-5,30, "HP");
			break;
		case 15*60:

			setEnemy(16, 0, 4, 5,-5,45, "Attack");
			break;
		case 20*60:

			setEnemy(16, -10, 4, 5,-5,45, "Life");
			setEnemy(16, 10, 4, 5,-5,45, "Attack");
			break;
		case 30*60:

			for(double i=1,n=5;i<=n;i++)setEnemy(16, 10, 4, 5,-6*(i/n),45, "HP");
			break;
		case 31*60:

			for(double i=1,n=5;i<=n;i++)setEnemy(16, -10, 4, 5,-6*(i/n),45, "Attack");
			break;
		case 35*60:

			for(double i=1,n=5;i<=n;i++)setEnemy(16, 10, 2, 5,-6*(i/n),i*15, "Attack");
			break;
		case 40*60:

			for(double i=1,n=5;i<=n;i++)setEnemy(16, 5, 3, 5,-10*(i/n),i*15, "HP");
			break;
		case 45*60:

			for(double i=1,n=5;i<=n;i++)setEnemy(16, 50, 2, 5,-7,i*-15, "Attack");
			for(double i=1,n=5;i<=n;i++)setEnemy(16, 0, 2, 5,-7,i*-15, "Attack");
			break;
		case 47*60:
			for(double i=1,n=5;i<=n;i++)setEnemy(16, 10, 1, 5,-10*(i/n),i*-15, "Attack");

			break;
		case 50*60:
			for(double i=1,n=10;i<=n;i++)setEnemy(16, 15, 1, 5,-10,45+i*-15, "Attack");

			break;
		case 55*60:
			for(double i=1,n=10;i<=n;i++)setEnemy(16, 0, 1, 5,-10,45+i*-15, "Attack");
			for(double i=1,n=10;i<=n;i++)setEnemy(16, 2, 1, 5,-10,45+i*-15, "Attack");
			break;
		}

		//ゲーム画面の背景を時間経過によって動かす
		background.display();

		if (virtualController.isKeyDown(Event.ENTER)) {
			ending();
		}
		if(RPGContainer.Zanki<0) {
			go();
		}

		//自機
		myShipScript.move(virtualController);
		item.move();

		//敵
		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).motion(interval);
		}

		//弾
		for (int i = 0; i < myBulletList.size(); i++) {
			myBulletList.get(i).move(interval);
		}
		//アイテム
		for (int i = 0; i < itemList.size(); i++) {
			itemList.get(i).move();
		}
		enemySetFrame++;

	}
	/**
	 * 敵を置く関数
	 * x座標,y座標,番号(1～4),体力,スピード(負の値で左に進む)，角度(度数法),落とすアイテム
	 * "Attack" "HP" "Life" "Heal"
	 */
	void setEnemy(double x, double y,int enemyNum, int _enemyHP, double _speed, double _angle, String itemname) {
		EnemyScript enemy;
		String imageFile;
		imageFile="data\\sozai\\enemy"+enemyNum+".png";
		System.out.println(imageFile);
		enemy = new EnemyScript(universe, myShipScript, myBulletList,imageFile, _enemyHP, _speed, _angle, itemname);
		enemy.setPosition(x, y);
		universe.place(enemy);
		enemyList.add(enemy);

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
