/*******************************************************************************
 * Copyright (c) 2010, ARMonkeyKit
 *  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are
 *  met:
 *  
 *  Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  
 *  Neither the name of 'ARMonkeyKit' nor the names of its contributors
 *  may be used to endorse or promote products derived from this software
 *  without specific prior written permission.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 *  TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package armonkeykit.core.app.utils;

import java.util.HashMap;
import java.util.Map;

import com.jme.scene.Node;

import armonkeykit.core.events.IEventListener;
import armonkeykit.core.events.MarkerChangedEvent;
import armonkeykit.core.markers.Marker;

public class MarkerConfidenceTracker implements IEventListener {
	//TODO: consider a better implementation strategy for this
	Map<String, Node> markerToNode = new HashMap<String, Node>();
	
	@Override
	public void associate(Marker m, Node n) {
		markerToNode.put(m.getUniqueID(), n);
	}

	@Override
	public void markerChanged(MarkerChangedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markerRemoved(Marker m) {
		// TODO Auto-generated method stub
		
	}

}
