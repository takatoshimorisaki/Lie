package mori.Lie.NodeTools;

import mori.Lie.Node;

public class IncludeCheckerMultiMono {

	private IncludeCheckerMonoMono mMonoMono = new IncludeCheckerMonoMono();
	
	public int mExe(
			Node aLargerNode,
			Node aSmallerNode
	)throws Exception{
		int ans = -1;
	
		int size = aLargerNode.mSubNodes.size();
		
		for(int id = 0; id < size; id++){
			
			Node node = aLargerNode.mGetSubNode(id);
			
			ans = mMonoMono.mExe(node, aSmallerNode);
			
			if(ans >= 0){
				
				ans = id;
				
				break;
			}
		}
		
		return ans;
	}
}
