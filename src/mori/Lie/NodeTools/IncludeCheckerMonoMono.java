package mori.Lie.NodeTools;

import mori.Lie.Node;

public class IncludeCheckerMonoMono {

	public int mExe(
			Node aLargerNode,
			Node aSmallerNode
	)throws Exception{
		int ans = -1;

		if(aLargerNode.mToken.equals(aSmallerNode.mToken)){
			
			if(aLargerNode.mPower > 0
			&& aSmallerNode.mPower > 0
			&& aLargerNode.mPower >= aSmallerNode.mPower){
				
				ans = 0;
				
			}else
			if(aLargerNode.mPower < 0
			&& aSmallerNode.mPower < 0
			&& aLargerNode.mPower <= aSmallerNode.mPower){
		
				ans = 0;
			}
		}
		
		return ans;
	}
}
