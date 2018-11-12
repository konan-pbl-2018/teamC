package template.shooting2D;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.image.TextureLoader;

import framework.game2D.Sprite;

public class Back extends Sprite {

	public Back(String imageFile, float scale) {
		this.scale = scale;
		transformGroup = new TransformGroup();
		Appearance appearance = new Appearance();

		if (imageFile != null){
			TextureLoader loader = new TextureLoader(imageFile, TextureLoader.BY_REFERENCE, null);
			appearance.setTexture(loader.getTexture());
			appearance.setCapability(Appearance.ALLOW_TEXTURE_READ);
			appearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		}

		Material material = new Material();
		material.setLightingEnable(false);
		material.setDiffuseColor(0.0f, 0.0f, 0.0f);
		material.setAmbientColor(0.0f, 0.0f, 0.0f);
		material.setSpecularColor(0.0f, 0.0f, 0.0f);
		material.setEmissiveColor(1.0f, 1.0f, 1.0f);
		material.setShininess(1.0f);
		appearance.setMaterial(material);

		TextureAttributes ta = new TextureAttributes();
		ta.setTextureMode(TextureAttributes.REPLACE);
		appearance.setTextureAttributes(ta);

		TransparencyAttributes transAttributes = new TransparencyAttributes();

		transAttributes.setCapability(TransparencyAttributes.ALLOW_BLEND_FUNCTION_READ);
		transAttributes.setCapability(TransparencyAttributes.ALLOW_BLEND_FUNCTION_WRITE);
		transAttributes.setCapability(TransparencyAttributes.ALLOW_MODE_READ);
		transAttributes.setCapability(TransparencyAttributes.ALLOW_MODE_WRITE);
		transAttributes.setCapability(TransparencyAttributes.ALLOW_VALUE_READ);
		transAttributes.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);

		transAttributes.setTransparencyMode(TransparencyAttributes.BLENDED);
		transAttributes.setTransparency(0.0f);
		// transAttributes.setSrcBlendFunction(TransparencyAttributes.BLEND_SRC_ALPHA);
		transAttributes.setDstBlendFunction(TransparencyAttributes.BLEND_SRC_ALPHA);
		appearance.setTransparencyAttributes(transAttributes);

		box = new Box(1.8f * scale, 1.0f * scale, 0.0f, Box.GENERATE_TEXTURE_COORDS | Box.GENERATE_NORMALS, appearance);///////////
		transformGroup.addChild(box);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		position.set(0.0, 0.0);
		setPosition(0.0, 0.0, 0.0);
		velocity.set(0.0, 0.0);
		setCollisionRadius(1.0);	}
	public void display() {
		setPosition(10.0 - ((double)TemplateShooting2DMultiStates.PlayTimeCount)/4000.0 , 0);
	}

}
