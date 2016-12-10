package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.expander.Holder.mExpanderMono;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mNodeSplitter;
import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.tools.Rational;

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
			
			if(rtn == true){
				
				if(newNode.mNodeType == NUMBER_NODE){
					
					aDestNode.mCoef *= newNode.mCoef;
					
					aDestNode.mSubNodes.remove(id);
					
				}else
				if(newNode.mNodeType == MONO_NODE){
				
					aDestNode.mCoef *= newNode.mCoef;
					
					node.mCoef = 1.0;
					
					node.mToken = new String(newNode.mToken);
					
					node.mPower = new Rational(newNode.mPower);

				}else
				if(newNode.mNodeType == MULTI_NODE
				|| newNode.mNodeType == POLY_NODE){

					if(aDestNode.mSubNodes.size() > 1){
						
						Node[] splitNode = mNodeSplitter.mExe(id, aDestNode);
						
						if(splitNode[0] != null){
							
							mMultiplier.mExe(aDestNode, splitNode[0], newNode);
							
							if(splitNode[1] != null){
		
								mMultiplier.mExe(aDestNode, aDestNode, splitNode[1]);
							}	
						}else{
							
							mMultiplier.mExe(aDestNode, newNode, splitNode[1]);
						}
						
					}else{
						String errMsg = String.format("aDestNode %s", 
								aDestNode.toString());
						
						throw new Exception();
					}

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
