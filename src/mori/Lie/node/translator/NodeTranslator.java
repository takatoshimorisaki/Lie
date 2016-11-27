package mori.Lie.node.translator;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.translator.Holder.mMultiMonoTranslator;
import mori.Lie.Node;

public class NodeTranslator {

	public void mExe(
		Node aDestNode,
		Node aSrcNode
	)throws Exception{

		mFactory.mCopy(aDestNode, aSrcNode);
		
		if(aSrcNode.mNodeType == MULTI_NODE){
			
			if(aDestNode.mSubNodes.size() == 0){
			
				aDestNode.mNodeType = NUMBER_NODE;
						
			}else
			if(aDestNode.mSubNodes.size() == 1){
				
				mMultiMonoTranslator.mExe(aDestNode, aSrcNode);
				
			}else{
			
				aDestNode.mNodeType = MULTI_NODE;
			}
			
		}else{
			throw new Exception("not implemented.");
		}
	}
}
