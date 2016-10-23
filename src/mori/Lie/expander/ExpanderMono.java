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
		Node leftNode,
		Node rightNode
	)throws Exception{
		boolean expanded = false;

		mFactory.mCopy(aDestNode, arg);
		
		if(aDestNode.mToken.equals(leftNode.mToken)){
			
			if(aDestNode.mPower > 0
			&& leftNode.mPower > 0
			&& aDestNode.mPower >= leftNode.mPower){
				
				aDestNode.mCoef /= leftNode.mCoef;
				
				aDestNode.mPower -= leftNode.mPower;
				
				expanded = true;
				
			}else
			if(aDestNode.mPower < 0
			&& leftNode.mPower < 0
			&& aDestNode.mPower <= leftNode.mPower){

				aDestNode.mCoef /= leftNode.mCoef;
				
				aDestNode.mPower -= leftNode.mPower;
								
				expanded = true;
			}
		}
		
		if(expanded == true){

			if(aDestNode.mPower == 0){
				
				aDestNode.mToken = null;
				
				aDestNode.mPower = 1;
				
				aDestNode.mNodeType = NUMBER_NODE;
			}
			
			aDestNode = mMultiplier.mExe(aDestNode, rightNode);
		}
		
		return expanded;
	}
}
