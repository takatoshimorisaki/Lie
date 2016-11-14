package mori.Lie.multiplier;

import static mori.Lie.Node.MULTINOMIAL_NODE;
import static mori.Lie.node.tools.Holder.mFactory;

import mori.Lie.Node;

public class MultiplierNumMulti {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		aDestNode.mNodeType = MULTINOMIAL_NODE;
		
		aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
						
		for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
			
			Node node = (Node)aAnoNode.mGetSubNode(cnt);
			
			aDestNode.add( mFactory.mExe(node) );
		}
	}
}
