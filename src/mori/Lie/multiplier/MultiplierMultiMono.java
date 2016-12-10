package mori.Lie.multiplier;

import static mori.Lie.Node.MONOMIAL_NODE;
import static mori.Lie.Node.MULTINOMIAL_NODE;
import static mori.Lie.node.tools.Holder.mFactory;

import mori.Lie.Node;

public class MultiplierMultiMono {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		aDestNode.mNodeType = MULTINOMIAL_NODE;
		
		aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;

		int oneSize = aOneNode.mSubNodes.size();
		
		for(int cnt = 0; cnt < oneSize - 1; cnt++){
			
			Node node =(Node)aOneNode.mGetSubNode(cnt);

			aDestNode.add( mFactory.mExe(node) );
		}
		
		Node oneSubNode = aOneNode.mGetSubNode(oneSize - 1);
		
		if(oneSubNode.mToken.equals(aAnoNode.mToken)){

			Node node2 = new Node();
			
			node2.mNodeType = MONOMIAL_NODE;
			
			node2.mToken = new String( aAnoNode.mToken );
			
			node2.mPower = oneSubNode.mPower.mAdd(aAnoNode.mPower);
			
			aDestNode.add( node2 );
			
		}else{

			aDestNode.add( mFactory.mExe(oneSubNode) );
		
			Node node2 = mFactory.mExe(aAnoNode);
			
			node2.mCoef = 1.0;
			
			aDestNode.add( node2 );
		}
	}
}
