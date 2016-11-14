package mori.Lie.multiplier;

import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.Node.MULTINOMIAL_NODE;
import static mori.Lie.node.tools.Holder.mFactory;

import mori.Lie.Node;

public class MultiplierMultiMulti {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		aDestNode.mNodeType = MULTINOMIAL_NODE;
		
		aDestNode.mCoef = aOneNode.mCoef * aAnoNode.mCoef;
		
		Node oneLastNode = aOneNode.mGetSubNode(aOneNode.mSubNodes.size() - 1);
		
		Node anoFirstNode = aAnoNode.mGetSubNode(0);
		
		if(oneLastNode.mToken.equals(anoFirstNode.mToken)){
			
			for(int cnt = 0; cnt < (aOneNode.mSubNodes.size() - 1); cnt++){
				
				Node node =(Node)aOneNode.mGetSubNode(cnt);
				
				aDestNode.add( mFactory.mExe(node) );
			}
			
			Node multipliedNode = mMultiplier.mExe(oneLastNode, anoFirstNode);

			aDestNode.add( multipliedNode );

			for(int cnt = 1; cnt < aAnoNode.mSubNodes.size(); cnt++){
				
				Node node =(Node)aAnoNode.mGetSubNode(cnt);
				
				aDestNode.add( mFactory.mExe(node) );
			}
		}else{
			
			for(int cnt = 0; cnt < aOneNode.mSubNodes.size(); cnt++){
				
				Node node =(Node)aOneNode.mGetSubNode(cnt);
				
				aDestNode.add( mFactory.mExe(node) );
			}
			
			for(int cnt = 0; cnt < aAnoNode.mSubNodes.size(); cnt++){
				
				Node node =(Node)aAnoNode.mGetSubNode(cnt);
				
				aDestNode.add( mFactory.mExe(node) );
			}
		}		
	}
}
