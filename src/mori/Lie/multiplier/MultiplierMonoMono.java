package mori.Lie.multiplier;

import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.tools.Rational;

public class MultiplierMonoMono {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		if(aOneNode.mToken.equals(aAnoNode.mToken)){
			
			aDestNode.mNodeType = MONOMIAL_NODE;

			aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
			
			aDestNode.mToken = new String(aOneNode.mToken);
			
			aDestNode.mPower = aOneNode.mPower.mAdd(aAnoNode.mPower);
			
		}else{
			
			aDestNode.mNodeType = MULTINOMIAL_NODE;
			
			aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
			
			Node node1 = new Node();
			
			node1.mNodeType = MONOMIAL_NODE;
			
			node1.mToken = new String( aOneNode.mToken );
			
			node1.mPower = new Rational( aOneNode.mPower );
			
			aDestNode.add( node1 );
			
			Node node2 = new Node();
			
			node2.mNodeType = MONOMIAL_NODE;
			
			node2.mToken = new String( aAnoNode.mToken );
			
			node2.mPower = new Rational( aAnoNode.mPower );
			
			aDestNode.add( node2 );
		}
	}
}
