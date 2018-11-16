package template.shooting2D;

import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.model3D.Universe;

public class Item extends Sprite {
	MyShipScript myshipScript;//プレイヤー
	public String itemName;
	ArrayList<Item> itemList;
	int upValue;//上昇値
	double fallSpeed=5;//落ちるスピード
	int frameCount;//経過フレーム
	Universe universe;
	public Item(Universe u, MyShipScript _s, String _itemName) {
		super(initPicture(_itemName));
		myshipScript = _s;
		itemName = _itemName;

		frameCount = 0;
		universe=u;


	}
	public static String initPicture(String s) {
		if (s.equals("Attack")) {
			return "data\\\\sozai\\\\kougeki UP 25%.png";
		}
		else if(s.equals("HP")) {
			return "data\\\\sozai\\\\体力25%.png";
		}else if(s.equals("Life")) {
			return "data\\\\sozai\\\\zanki final.png";
		}else if(s.equals("Heal")) {
			return "data\\\\sozai\\\\kaihuku final.png";

		}
		return "";
	}

	public void setValueMyShip() {
		if (itemName.equals("Attack")) {
			MyShipScript.attackNumber+=1;
			RPGContainer.MyAttack+=2;
		}
		else if(itemName.equals("HP")) {
			MyShipScript.maxHPValue+=10;
			RPGContainer.MyHp+=10;
			upValue=20;

		}else if(itemName.equals("Life")) {
			MyShipScript.lifeNumber+=1;
			RPGContainer.Zanki+=1;

		}else if(itemName.equals("Heal")) {
			MyShipScript.healItemNumber+=1;
			RPGContainer.Item+=1;

		}
	}

	public void move() {
		fall();
		collision();
		frameCount++;
	}

	public void fall() {
		double speed, argument, _argument,second;
		second=300;
		argument = ((double)frameCount/second )* Math.PI;
		_argument = ((double)frameCount - 1.0)/second * Math.PI;

		speed = second/2*fallSpeed * (Math.sin(argument) - Math.sin(_argument));
		if (argument > Math.PI)speed = -fallSpeed;
		moveUp(speed);
	}
	public void collision() {
		if (checkCollision(myshipScript)) {
			setValueMyShip();
			TemplateShooting2DMultiStates.itemList.remove(this);
			universe.displace(this);
		}
		if(getPosition().getY() <= -1.0 * TemplateShooting2D.RANGE / 2) {
			TemplateShooting2DMultiStates.itemList.remove(this);
			universe.displace(this);
		}
	}

}
