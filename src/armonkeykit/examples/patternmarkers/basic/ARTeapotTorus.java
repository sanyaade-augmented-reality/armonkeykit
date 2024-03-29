/*******************************************************************************
*    This file is part of ARMonkeyKit.
*
*    ARMonkeyKit is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    ARMonkeyKit is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with ARMonkeyKit.  If not, see <http://www.gnu.org/licenses/>.
******************************************************************************/
package armonkeykit.examples.patternmarkers.basic;

import com.jme.bounding.BoundingSphere;
import com.jme.math.FastMath;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.shape.Teapot;
import com.jme.scene.shape.Torus;

import armonkeykit.core.app.ARMonkeyKitApp;
import armonkeykit.core.app.utils.eventlisteners.NodeRotateTranslateListener;
import armonkeykit.core.markerprocessor.pattern.PatternMarkerProcessor;
import armonkeykit.core.markers.PatternMarker;

/**
 * ARTeapotTorus. A simple example program which uses two Pattern Markers to
 * display a teapot and torus model
 * 
 * @author Adam Clarkson
 * 
 */
public class ARTeapotTorus extends ARMonkeyKitApp {
//TODO update documentation
	
	// marker processor to be used for this application.
	private PatternMarkerProcessor markerProcessor;
	// event listener to use with the system
	private NodeRotateTranslateListener rtl;

	public ARTeapotTorus() {
		super();
		showCamera = true; // enable or disable camera feed
	    showSceneViewer = true; // enable or disable SceneMonitor
	}

	/**
	 * This must be called in order to state what type of marker processor you
	 * want to use. This should be initPatternProcessor for Pattern markers, or
	 * initIDMarkerProcessor for NyID Model 2 markers.
	 * 
	 * Also registers EventListeners to be used by the system.
	 */
	@Override
	protected void simpleInitARSystem() {
		markerProcessor = initPatternProcessor();
		rtl = new NodeRotateTranslateListener(true);
		markerProcessor.registerEventListener(rtl);
	}

	@Override
	protected void addMarkers() {
		/**
		 * This creates the marker objects and registers them to the system
		 */

		PatternMarker kanji = markerProcessor.createMarkerObject("kanji", 16,
				"ardata/patt.kanji", 80);
		markerProcessor.registerMarker(kanji);
		
		PatternMarker hiro = markerProcessor.createMarkerObject("hiro", 16,
				"ardata/patt.hiro", 80);
		markerProcessor.registerMarker(hiro);
		
		/**
		 * Create some content to attach to the markers
		 * 
		 */
		Node teapotAffectedNode = new Node("Affected Teapot Node");
		Teapot tp = new Teapot("ShinyTeapot");
		tp.setModelBound(new BoundingSphere());
		tp.updateModelBound();
		tp.setLocalScale(10f);
		// rotate our teapot so its base sits on the marker
		Quaternion q = new Quaternion();
		q = q.fromAngleAxis(-FastMath.PI / 2, new Vector3f(1f, 0f, 0f));
		tp.setLocalRotation(q);

		teapotAffectedNode.attachChild(tp);
		rootNode.attachChild(teapotAffectedNode);

		Node torusAffectedNode = new Node("Affected Torus Node");
		Torus torus = new Torus("Torus", 12, 40, 1.5f, 3f);
		torus.setLocalScale(10f);
		torus.setModelBound(new BoundingSphere());
		torus.updateModelBound();
		torusAffectedNode.attachChild(torus);
		rootNode.attachChild(torusAffectedNode);

		/**
		 * Use the associate method of the event listener to create a
		 * relationship between a marker object and the ARContentNode we created
		 * for that marker.
		 */
		rtl.associate(kanji, teapotAffectedNode);
		rtl.associate(hiro, torusAffectedNode);

		/**
		 * This method must be called after adding markers, to ensure that the
		 * detection list is up to date
		 */
		markerProcessor.finaliseMarkers();

	}

	@Override
	protected void callUpdates() {
	}

	public static void main(String[] args) {
		ARTeapotTorus app = new ARTeapotTorus();
		app.setConfigShowMode(ConfigShowMode.AlwaysShow);
		app.start();
	}

	@Override
	protected void configOptions() {
		
	}

}
