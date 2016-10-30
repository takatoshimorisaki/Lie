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

		out.printf("ExpanderMultiMono arg %s leftNode %s rightNode %s\n",
				arg.toString(),
				leftNode.toString(),
				rightNode.toString());
		
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
				if(newNode.mNodeType == MULTI_NODE
				|| newNode.mNodeType == POLY_NODE){

					aDestNode.mSubNodes.remove(id);
					
					if(aDestNode.mSubNodes.size() > 1){
						
						Node[] splitNode = mNodeSplitter.mExe(id, aDestNode);
						
						mMultiplier.mExe(aDestNode, splitNode[0], newNode);
						
						if(splitNode.length > 1){
	
							mMultiplier.mExe(aDestNode, newNode, splitNode[1]);
						}
					}else{
						
						mMultiplier.mExe(aDestNode, aDestNode.mGetSubNode(0), newNode);
					}
				
				}else{
					throw new Exception("not implemented.");
				}
								
				expanded = true;
				
				break;
			}// if rtn
		}// for id

		out.printf("ExpanderMultiMono2 expanded %b aDestNode %s arg %s leftNode %s rightNode %s\n",
				expanded,
				aDestNode.toString(),
				arg.toString(),
				leftNode.toString(),
				rightNode.toString());
		
		return expanded;
	}
}
