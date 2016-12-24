package mori.Lie.multiplier;

import static mori.Lie.NodeType.*;
import static mori.Lie.node.tools.Holder.mFactory;
import mori.Lie.Node;
import mori.tools.Rational;

public class MultiplierMonoMulti {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		Node anoSubNode = aAnoNode.mGetSubNode(0);
		
		if(aOneNode.mToken.equals(anoSubNode.mToken)){

			aDestNode.mNodeType = MULTINOMIAL_NODE;
			
			aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
			
			Node subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = new String(aOneNode.mToken);
			
			subNode.mPower = aOneNode.mPower.mAdd(anoSubNode.mPower);

			if(subNode.mPower.mEquals(0) == false){
				aDestNode.add(subNode);
			}
			
			for(int cnt = 1; cnt < aAnoNode.mSubNodes.size(); cnt++){
				
				Node node2 = (Node)aAnoNode.mGetSubNode(cnt);
				
				aDestNode.add( mFactory.mExe(node2) );
			}
			
		}else{
			
			aDestNode.mNodeType = MULTINOMIAL_NODE;
			
			aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
			
			Node node1 = new Node();
			
			node1.mNodeType = MONOMIAL_NODE;
			
			node1.mToken = new String( aOneNode.mToken );
			
			node1.mPower = new Rational(aOneNode.mPower);
			
			aDestNode.add(node1);
			
			for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
				
				Node node2 = (Node)aAnoNode.mGetSubNode(cnt);
				
				aDestNode.add( mFactory.mExe(node2) );
			}
		}
	}
}
