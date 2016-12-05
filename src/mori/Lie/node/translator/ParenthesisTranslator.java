package mori.Lie.node.translator;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import mori.Lie.Node;

public class ParenthesisTranslator {

	public void mExe(
		Node aDestNode,
		Node aSrcNode
	)throws Exception{

		if(aSrcNode.mSubNodes.size() == 1){
			
			Node subNode = aSrcNode.mGetSubNode(0);
			
			if(subNode.mNodeType == NULL_NODE){
				
				aDestNode.mInit();
			
			}else
			if(subNode.mNodeType == NUMBER_NODE
			|| subNode.mNodeType == MONO_NODE
			|| subNode.mNodeType == MULTI_NODE){
				
				mFactory.mCopy(aDestNode, subNode);
			}
		}
	}
}
