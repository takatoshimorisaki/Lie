package mori.Lie.include;

import mori.Lie.Node;
import mori.Lie.node.tools.EqualNomialChecker;

public class IncludeCheckerMultiMulti {

	private IncludeCheckerMonoMono mMonoMono = new IncludeCheckerMonoMono();
	
	private EqualNomialChecker mEqualNomialChecker = new EqualNomialChecker();
	
	public int mExe(
			Node aLargerNode,
			Node aSmallerNode
	)throws Exception{
		int ans = -1;
		int startPos = 0;
		
		int largerSize = aLargerNode.mSubNodes.size();
		
		int smallerSize = aSmallerNode.mSubNodes.size();
		
		while(largerSize - startPos >= smallerSize){
			
			for(int id = startPos; 
				id < largerSize && (id - startPos) < smallerSize;
				id++){
				
				Node lNode = aLargerNode.mGetSubNode(id);
				
				Node sNode = aSmallerNode.mGetSubNode(id - startPos);
				
				if(id == startPos
				|| (id - startPos) == (smallerSize - 1)){
					
					int includeId = mMonoMono.mExe(lNode, sNode);
					
					if(includeId < 0){
						
						ans = -1;
						
						break;
					}{
						if(id == startPos){
							
							ans = startPos;
						}
					}
				}else{
					
					if(mEqualNomialChecker.mExe(lNode, sNode)){
						// nothing to do.
					}else{
						ans = -1;
						
						break;
					}
				}
			}
	
			if(ans >= 0){
				break;
			}
			
			startPos++;
		}
	
		return ans;
	}
}
