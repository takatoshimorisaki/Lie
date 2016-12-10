package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.node.tools.Holder.mFactory;
import mori.Lie.Node;
import mori.tools.Rational;

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
			
			if(aDestNode.mPower.mGreater(0)
			&& leftNode.mPower.mGreater(0)
			&& aDestNode.mPower.mGreaterEqual(leftNode.mPower)){
				
				aDestNode.mCoef /= leftNode.mCoef;
				
				aDestNode.mPower = aDestNode.mPower.mSubtract(leftNode.mPower);
				
				expanded = true;
				
			}else
			if(aDestNode.mPower.mLesser(0)
			&& leftNode.mPower.mLesser(0)
			&& aDestNode.mPower.mLesserEqual(leftNode.mPower)){

				aDestNode.mCoef /= leftNode.mCoef;

				aDestNode.mPower = aDestNode.mPower.mSubtract(leftNode.mPower);
								
				expanded = true;
			}
		}
		
		if(expanded == true){

			if(aDestNode.mPower.mEquals(0)){
				
				aDestNode.mToken = null;
				
				aDestNode.mPower.mInit();
				
				aDestNode.mNodeType = NUMBER_NODE;
			}

			Node multiplyedNode = mMultiplier.mExe(aDestNode, rightNode);

			mFactory.mCopy(aDestNode, multiplyedNode);
		}

		return expanded;
	}
}
