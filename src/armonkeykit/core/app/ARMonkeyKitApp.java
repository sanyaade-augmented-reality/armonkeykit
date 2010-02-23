package armonkeykit.core.app;

import jp.nyatla.nyartoolkit.NyARException;
import jp.nyatla.nyartoolkit.core.param.NyARPerspectiveProjectionMatrix;
import jp.nyatla.nyartoolkit.qt.sample.JmeNyARParam;
import armonkeykit.videocapture.CaptureQuad;

import com.jme.app.SimpleGame;
import com.jme.input.NodeHandler;
import com.jme.light.DirectionalLight;
import com.jme.light.PointLight;
import com.jme.math.Matrix3f;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.shape.Torus;

public abstract class ARMonkeyKitApp extends SimpleGame {

	protected CaptureQuad cameraBG;

	
	private static final int CAMERA_WIDTH = 640;
	private static final int CAMERA_HEIGHT = 480;
	private final String PARAM_FILE = "ardata/camera_para.dat";

	protected JmeNyARParam jmeARParameters;
	

	public ARMonkeyKitApp() {		
		jmeARParameters = new JmeNyARParam();
		try {
			jmeARParameters.loadARParamFromFile(PARAM_FILE);
		} catch (NyARException e) {
			e.printStackTrace();
			//TODO: think about this a bit
		}
		jmeARParameters.changeScreenSize(CAMERA_WIDTH, CAMERA_HEIGHT);
	}

	protected void lightSetup(){

		PointLight pl = new PointLight();
		pl.setAmbient(new ColorRGBA(0.75f, 0.75f, 0.75f, 1));
		pl.setDiffuse(new ColorRGBA(1, 1, 1, 1));
		pl.setLocation(new Vector3f(50, 0, 0));
		pl.setEnabled(true);

		DirectionalLight dl = new DirectionalLight();
		dl.setAmbient(new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
		pl.setDiffuse(new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
		dl.setEnabled(true);
		dl.setDirection(new Vector3f(0, -1, 1));

		lightState.attach(pl);
		lightState.attach(dl);
	}

	protected void cameraSetup() {
		NyARPerspectiveProjectionMatrix m2 = jmeARParameters.getPerspectiveProjectionMatrix();

		cameraBG = new CaptureQuad("Background", CAMERA_WIDTH, CAMERA_HEIGHT, 60f);
		cameraBG.updateGeometry(CAMERA_WIDTH *4 , CAMERA_HEIGHT *4);
		cameraBG.setCastsShadows(false);
		Matrix3f m = new Matrix3f();
		m.fromAngleAxis((float) Math.toRadians(180), new Vector3f(0, 0, 1));
		cameraBG.setLocalRotation(m);
		cameraBG.setLocalTranslation(new Vector3f(0, 0, (float) -m2.m00 * 4));

		rootNode.attachChild(cameraBG);
		
		input = new NodeHandler(new Torus(), 10, 2);//this seems to override mouse control of camera
		
		float[] ad = jmeARParameters.getCameraFrustum();
		cam.setFrustum(ad[0], ad[1], ad[2], ad[3], ad[4], ad[5]);
	}
	
	protected abstract void simpleUpdate();
	
	protected abstract void simpleInitGame();

	
}
