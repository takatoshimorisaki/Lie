package mori.Lie.divider;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import mori.Lie.Node;

public class DividerNumMulti {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		aDestNode.mNodeType = MULTI_NODE;
		
		aDestNode.mCoef = aOneNode.mCoef / aAnoNode.mCoef;
		
		aDestNode.mToken = null;
		
		aDestNode.mPower = 1;
		
		for(int id = 0; id < aAnoNode.mSubNodes.size(); id++){
			
			if(aDestNode.mSubNodes == null){
				
				aDestNode.mSubNodes = new java.util.Vector<Node>();
			}
			
			Node node = mFactory.mExe( aAnoNode.mGetSubNode(id) );
			
			node.mPower = - node.mPower;
			
			aDestNode.add(node);
		}
	}
}
