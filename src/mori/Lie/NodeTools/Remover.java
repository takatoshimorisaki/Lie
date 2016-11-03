package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import static mori.Lie.NodeTools.Holder.mFactory;
import static mori.Lie.node.translator.Holder.mNodeTranslator;
import mori.Lie.Node;

public class Remover {

	public Node[] mExe(
			Node aDestNode,
			Node aLargerNode,
			Node aSmallerNode,
			int aPos
	)throws Exception{
		Node[] splitedNode = new Node[2];
		
		aDestNode.mInit();

		if(aLargerNode.mNodeType == MULTI_NODE
		&& aSmallerNode.mNodeType == MULTI_NODE){

			aDestNode.mCoef = aLargerNode.mCoef / aSmallerNode.mCoef;
						
			for(int id = 0; id < aLargerNode.mSubNodes.size(); id++){
				
				if(id == aPos){
					
					Node aLargerSubNode = aLargerNode.mGetSubNode(id);
					
					Node destSubNode = new Node();
					
					destSubNode.mToken = new String(aLargerSubNode.mToken);
					
					destSubNode.mPower = aLargerSubNode.mPower - aSmallerNode.mPower;
					
					if(destSubNode.mPower != 0){
						
						aDestNode.add(destSubNode);

						if(splitedNode[0] == null){
							splitedNode[0] = new Node();
						}
						
						splitedNode[0].add(destSubNode);
					}
				}else
				if(aPos < id
				&& id < (aPos + aSmallerNode.mSubNodes.size())){
					
					Node destSubNode = mFactory.mExe(aLargerNode.mGetSubNode(id));

					if(splitedNode[1] == null){
						splitedNode[1] = new Node();
					}
					
					splitedNode[1].add(destSubNode);
					
				}else{
					Node destSubNode = mFactory.mExe(aLargerNode.mGetSubNode(id));
					
					aDestNode.add(destSubNode);

					if(splitedNode[1] == null){
						splitedNode[1] = new Node();
					}
					
					splitedNode[0].add(destSubNode);
				}
			}// for id

			mNodeTranslator.mExe(aDestNode, aDestNode);
			
			for(int id = 0; id < splitedNode.length; id++){
				
				if(splitedNode[id] != null){
				
					mNodeTranslator.mExe(splitedNode[id], splitedNode[id]);
				}
			}

		}else{
			throw new Exception();
		}
		
		return splitedNode;
	}
}
