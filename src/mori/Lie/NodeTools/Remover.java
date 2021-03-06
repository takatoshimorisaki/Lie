package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class Remover {

	private static Factory mFactory = new Factory();

	public void mExe(
			Node aDestNode,
			Node aLargerNode,
			Node aSmallerNode,
			int aPos
	)throws Exception{
		
		aDestNode.mInit();

		aDestNode.mCoef = aLargerNode.mCoef / aSmallerNode.mCoef;
		
		if(aDestNode.mNodeType == MULTI_NODE
		&& aSmallerNode.mNodeType == MULTI_NODE){
			
			for(int id = 0; id < aLargerNode.mSubNodes.size(); id++){
				
				if(id == aPos){
					
					Node aLargerSubNode = aLargerNode.mGetSubNode(id);
					
					Node destSubNode = new Node();
					
					destSubNode.mToken = new String(aLargerSubNode.mToken);
					
					destSubNode.mPower = aLargerSubNode.mPower - aSmallerNode.mPower;
					
					if(destSubNode.mPower != 0){
						
						aDestNode.add(destSubNode);
					}
				}else
				if(aPos < id
				&& id < (aPos + aSmallerNode.mSubNodes.size())){
					// nothing to do.
				}else{
					Node destSubNode = mFactory.mExe(aLargerNode.mGetSubNode(id));
					
					aDestNode.add(destSubNode);
				}
			}// for id

		}else{
			throw new Exception();
		}
	}
}
