package mori.Lie.include;

import mori.Lie.Node;
import mori.tools.Rational;

public class IncludeCheckerMonoMono {

	public int mExe(
			Node aLargerNode,
			Node aSmallerNode
	)throws Exception{
		int ans = -1;

		if(aLargerNode.mToken.equals(aSmallerNode.mToken)){
			
			if(aLargerNode.mPower.mGreater(0)
			&& aSmallerNode.mPower.mGreater(0)
			&& aLargerNode.mPower.mGreaterEqual(aSmallerNode.mPower)){
				
				ans = 0;
				
			}else
			if(aLargerNode.mPower.mLesser(0)
			&& aSmallerNode.mPower.mLesser(0)
			&& aLargerNode.mPower.mLesserEqual(aSmallerNode.mPower)){
		
				ans = 0;
			}
		}
		
		return ans;
	}
}
