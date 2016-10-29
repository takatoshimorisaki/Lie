package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import static mori.Lie.NodeTools.Holder.mFactory;
import mori.Lie.Node;

public class Remover {

	public Node[] mExe(
			Node aDestNode,
			Node aLargerNode,
			Node aSmallerNode,
			int aPos
	)throws Exception{
		Node[] splitedNode = new Node[2];
		
		splitedNode[0] = new Node();
		splitedNode[1] = new Node();
		
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

						splitedNode[0].add(destSubNode);
					}
				}else
				if(aPos < id
				&& id < (aPos + aSmallerNode.mSubNodes.size())){
					
					Node destSubNode = mFactory.mExe(aLargerNode.mGetSubNode(id));

					splitedNode[1].add(destSubNode);
					
				}else{
					Node destSubNode = mFactory.mExe(aLargerNode.mGetSubNode(id));
					
					aDestNode.add(destSubNode);
					
					splitedNode[0].add(destSubNode);
				}
			}// for id

		}else{
			throw new Exception();
		}
		
		return splitedNode;
	}
}
