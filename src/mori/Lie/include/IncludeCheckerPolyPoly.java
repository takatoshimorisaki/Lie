package mori.Lie.include;

import mori.Lie.Node;

public class IncludeCheckerPolyPoly {

	public int mExe(
			Node aLargerNode,
			Node aSmallerNode,
			IncludeChecker aIncludeChecker
	)throws Exception{
		int ans = -1;
	
		int smallerSize = aSmallerNode.mSubNodes.size();
		
		int largerSize = aLargerNode.mSubNodes.size();
		
		for(int sId = 0; sId < smallerSize; sId++){
		
			Node sSubNode = aSmallerNode.mGetSubNode(sId);
			
			int includePos = -1;
			
			for(int lId = 0; lId < largerSize; lId++){
			
				Node lSubNode = aLargerNode.mGetSubNode(lId);
				
				includePos = aIncludeChecker.mExe(lSubNode, sSubNode);
			}
			
			if(includePos < 0){
				break;
			}
			if(includePos >= 0 && sId == 0){
				ans = includePos;
			}
		}
		
		return ans;
	}
}
