package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.expander.Holder.mExpanderMono;
import static mori.Lie.Node.*;
import static mori.Lie.NodeTools.Holder.mFactory;
import static mori.Lie.NodeTools.Holder.mMultiplier;
import static mori.Lie.NodeTools.Holder.mNodeSplitter;
import mori.Lie.Node;

public class ExpanderMultiMono {
	
	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node leftNode,
		Node rightNode
	)throws Exception{
		boolean expanded = false;

		mFactory.mCopy(aDestNode, arg);
		
		Node newNode    = null;
	
		for(int id = 0; id < aDestNode.mSubNodes.size(); id++){
			
			Node node = aDestNode.mGetSubNode(id);
			
			newNode = new Node();
			
			boolean rtn = mExpanderMono.mExe(newNode, node, leftNode, rightNode);
			
			if(rtn = true){

				if(newNode.mNodeType == NUMBER_NODE){
					
					aDestNode.mCoef *= newNode.mCoef;
					
					aDestNode.mSubNodes.remove(id);
					
				}else
				if(newNode.mNodeType == MONO_NODE){
				
					aDestNode.mCoef *= newNode.mCoef;
					
					node.mCoef = 1.0;
					
					node.mToken = new String(newNode.mToken);
					
					node.mPower = newNode.mPower;

				}else
				if(newNode.mNodeType == MULTI_NODE){
					
					throw new Exception("not implemented.");
				}else
				if(newNode.mNodeType == POLY_NODE){
					throw new Exception("not implemented.");
				}else{
					throw new Exception("not implemented.");
				}
								
				expanded = true;
				
				break;
			}// if rtn
		}// for id

		return expanded;
	}
}
