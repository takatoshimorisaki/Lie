package mori.Lie.node.translator;

import static mori.Lie.Node.*;
import static mori.Lie.node.translator.Holder.mMultiMonoTranslator;
import mori.Lie.Node;

public class NodeTranslator {

	public void mExe(
		Node aDestNode,
		Node aSrcNode
	)throws Exception{

		if(aDestNode.mSubNodes.size() == 0){
		
			aDestNode.mNodeType = NUMBER_NODE;
					
		}else
		if(aDestNode.mSubNodes.size() == 1){
			
			mMultiMonoTranslator.mExe(aDestNode, aDestNode);
			
		}else{
		
			aDestNode.mNodeType = MULTI_NODE;
		}
		
	}
}
