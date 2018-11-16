package template.shooting2D;

import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.model3D.Universe;

public class Item extends Sprite {
	MyShipScript myshipScript;//プレイヤー
	String itemName;
	ArrayList<Item> itemList;
	int upValue;//上昇値
	double fallSpeed;//落ちるスピード
	int frameCount;//経過フレーム
	Universe universe;
	public Item(Universe u, String string, ArrayList<Item> il, MyShipScript _s, String _itemName, int value) {
		super(string);
		myshipScript = _s;
		itemName = _itemName;
		upValue = value;
		frameCount = 0;
		universe=u;
	}

	public void move() {
		fall();
		frameCount++;
	}

	public void fall() {
		double speed, argument, _argument,second;
		second=60;
		argument = ((double)frameCount/second )* Math.PI;
		_argument = ((double) (frameCount - 1)/second) * Math.PI;

		speed = fallSpeed * (Math.sin(argument) - Math.sin(_argument));
		if (argument > Math.PI)
			speed = -fallSpeed;
		moveUp(speed);
	}
	public void collision() {
		if (checkCollision(myshipScript)) {
			itemList.remove(this);
			universe.displace(this);
		}
	}

}
