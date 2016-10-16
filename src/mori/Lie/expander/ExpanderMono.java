package mori.Lie.expander;

import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.Lie.NodeTools.Factory;
import mori.Lie.NodeTools.Multiplier;

public class ExpanderMono {

	private static Factory mFactory = new Factory();
	
	private static Multiplier mMultiplier = new Multiplier();
	
	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node rightNode,
		Node leftNode
	)throws Exception{
		boolean expanded = false;

		mFactory.mCopy(aDestNode, arg);
		
		if(aDestNode.mToken.equals(rightNode.mToken)){
			
			if(aDestNode.mPower > 0
			&& rightNode.mPower > 0
			&& aDestNode.mPower >= rightNode.mPower){
				
				aDestNode.mCoef /= rightNode.mCoef;
				
				aDestNode.mPower -= rightNode.mPower;
				
				expanded = true;
				
			}else
			if(aDestNode.mPower < 0
			&& rightNode.mPower < 0
			&& aDestNode.mPower <= rightNode.mPower){

				aDestNode.mCoef /= rightNode.mCoef;
				
				aDestNode.mPower -= rightNode.mPower;
								
				expanded = true;
			}
		}
		
		if(expanded == true){

			if(aDestNode.mPower == 0){
				
				aDestNode.mToken = null;
				
				aDestNode.mPower = 1;
				
				aDestNode.mNodeType = NUMBER_NODE;
			}
			
			aDestNode = mMultiplier.mExe(aDestNode, leftNode);
		}
		
		return expanded;
	}
}
