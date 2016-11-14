package mori.Lie.multiplier;

import static mori.Lie.Node.MULTINOMIAL_NODE;
import static mori.Lie.node.tools.Holder.mFactory;
import mori.Lie.Node;

public class MultiplierMultiNum {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		aDestNode.mNodeType = MULTINOMIAL_NODE;
		
		aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
		
		for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
			
			Node node =(Node)aOneNode.mGetSubNode(cnt);

			aDestNode.add( mFactory.mExe(node) );
		}
	}
}
