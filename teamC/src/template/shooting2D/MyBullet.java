package template.shooting2D;

import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.game2D.Velocity2D;
import framework.model3D.Universe;

public class MyBullet extends Sprite  {
	Universe uni;
	public boolean onDisplay=false;
	public float speed;
	public float angle;
	public boolean shipToEnemy;
	ArrayList<MyBullet> myBulletList;

	public MyBullet(Universe u,String imageFile,ArrayList<MyBullet> mb,float _s,float _angle,boolean ste) {
		super(imageFile);
		uni=u;
		speed=_s;
		myBulletList=mb;
		shipToEnemy=ste;
		angle=_angle;
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}
	public void move(long interval) {

		if(!isInScreen((int)(TemplateShooting2D.RANGE*1.1),(int)(TemplateShooting2D.RANGE*1.1))) {
			onDisplay=false;
			uni.displace(this);
			myBulletList.remove(this);
		}
		if(!onDisplay)return;//��ʂɑ��݂��Ȃ���΂����ŏ������I����
		Velocity2D vel=this.getVelocity();
		vel.set(speed*Math.cos(angle),speed*Math.sin(angle));
		setVelocity(vel);
		super.motion(interval);
	}


	/**
	 * width��height�ŕ\�����E�B���h�E�T�C�Y���ɑ��݂��邩�𔻒肷��
	 *
	 * @param width
	 *            --- �E�B���h�E�̕�
	 * @param height
	 *            --- �E�B���h�E�̍���
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
