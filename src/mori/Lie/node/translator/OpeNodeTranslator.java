package mori.Lie.node.translator;

import static mori.Lie.Node.*;
import static mori.Lie.adder.Holder.mAdder;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.node.translator.Holder.mNodeTranslator;
import static mori.Lie.node.tools.Holder.mSubtracter;
import mori.Lie.Node;

public class OpeNodeTranslator {

	public void mExe(
		Node aDestNode,
		Node aSrcNode
	)throws Exception{
		
		Node leftNode = mNodeTranslator.mExe(aSrcNode.mGetSubNode(0));
		
		Node rightNode = mNodeTranslator.mExe(aSrcNode.mGetSubNode(1));
		
		if(leftNode.mNodeType == PARENTHESIS_NODE
		|| rightNode.mNodeType == PARENTHESIS_NODE){
			
			// nothing to do.
			
			return;
		}
		
		if(aSrcNode.mNodeType == OPE_ADD_NODE){
			
			mAdder.mExe(aDestNode, leftNode, rightNode);

		}else
		if(aSrcNode.mNodeType == OPE_SUB_NODE){
			
			mSubtracter.mExe(aDestNode, leftNode, rightNode);
			
		}else
		if(aSrcNode.mNodeType == OPE_MULTI_NODE){
			
			mMultiplier.mExe(aDestNode, leftNode, rightNode);
			
		}else{
			
			// nothing to do.
		}
	}
}
