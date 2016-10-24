package mori.Lie.include;

import mori.Lie.Node;

public class IncludeCheckerPoly {

	public int mExe(
			Node aLargerNode,
			Node aSmallerNode,
			IncludeChecker aIncludeChecker
	)throws Exception{
		int ans = -1;
	
		int size = aLargerNode.mSubNodes.size();
		
		for(int id = 0; id < size; id++){
			
			Node node = aLargerNode.mGetSubNode(id);
			
			ans = aIncludeChecker.mExe(node, aSmallerNode);
			
			if(ans >= 0){
				break;
			}
		}
		
		return ans;
	}
}
