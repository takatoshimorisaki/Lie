package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class IncludeChecker {

	public boolean mExe(
			Node aLargerNode,
			Node aSmallerNode
	)throws Exception{
		boolean ans = false;
		
		if(aLargerNode.mNodeType == MONO_NODE
		&& aSmallerNode.mNodeType == MONO_NODE){

			if(aLargerNode.mToken.equals(aSmallerNode.mToken)){
				
				if(aLargerNode.mPower > 0
				&& aSmallerNode.mPower > 0
				&& aLargerNode.mPower >= aSmallerNode.mPower){
					
					ans = true;
					
				}else
				if(aLargerNode.mPower < 0
				&& aSmallerNode.mPower < 0
				&& aLargerNode.mPower <= aSmallerNode.mPower){
			
					ans = true;
				}
			}
		}
		
		return ans;
	}
}
