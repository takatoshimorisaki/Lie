package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.node.tools.Holder.mFactory;

import mori.Lie.Node;

public class ExpanderMono {

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

			Node multiplyedNode = mMultiplier.mExe(aDestNode, rightNode);

			mFactory.mCopy(aDestNode, multiplyedNode);
		}

		return expanded;
	}
}
