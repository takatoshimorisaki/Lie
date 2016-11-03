package mori.Lie.node.translator;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class MultiMonoTranslator {

	public void mExe(
		Node aDestNode,
		Node aSrcNode
	)throws Exception{
		
		if(aSrcNode.mSubNodes.size() == 1){
			
			Node node = aSrcNode.mGetSubNode(0);
			
			aDestNode.mCoef = aSrcNode.mCoef;
			
			aDestNode.mToken = new String(node.mToken);
					
			aDestNode.mPower = node.mPower;
			
			aDestNode.mSubNodes = null;
			
			aDestNode.mNodeType = MONOMIAL_NODE;
			
		}else{
			
			throw new Exception();
		}
	}
}
