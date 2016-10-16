package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class NodeSplitter {

	private static Multiplier mMultiplier = new Multiplier();
	
	public Node[] mExe(
			int splitPos, 
			Node arg
	)throws Exception{

		Node[] ans = new Node[2];
		
		if(arg.mNodeType == MULTI_NODE){
			
			if(splitPos < 0
			|| splitPos > arg.mSubNodes.size() - 1){
				
				String errMsg = String.format("splitPos %d", splitPos);
				
				throw new Exception(errMsg);
			}else
			if(arg.mSubNodes.size() == 1){
				throw new Exception();
			}
			
			if(splitPos != 0){
				
				ans[0] = arg.mGetSubNode(0);
				
				for(int id = 1; id < splitPos; id++){
					
					Node node = arg.mGetSubNode(id);
					
					ans[0] = mMultiplier.mExe(ans[0], node);
				}
			}
			
			if(splitPos != (arg.mSubNodes.size()-1)){
				
				ans[1] = arg.mGetSubNode(splitPos + 1);
				
				for(int id = (splitPos + 2); id < arg.mSubNodes.size(); id++){

					Node node = arg.mGetSubNode(id);
					
					ans[1] = mMultiplier.mExe(ans[1], node);
				}
			}
		}else{
			throw new Exception();
		}
		
		return ans;
	}
}
