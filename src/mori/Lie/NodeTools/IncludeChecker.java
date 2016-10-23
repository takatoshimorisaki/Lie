package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class IncludeChecker {

	private IncludeCheckerMonoMono mMonoMono = new IncludeCheckerMonoMono();
	
	private IncludeCheckerMultiMono mMultiMono = new IncludeCheckerMultiMono();
	
	private IncludeCheckerMultiMulti mMultiMulti = new IncludeCheckerMultiMulti();
	
	public int mExe(
			Node aLargerNode,
			Node aSmallerNode
	)throws Exception{
		int ans = -1;
		
		if(aLargerNode.mNodeType == MONO_NODE){
			
			if(aSmallerNode.mNodeType == MONO_NODE){

				ans = mMonoMono.mExe(aLargerNode, aSmallerNode);
				
			}else{
				throw new Exception("not implemented.");
			}
		}else
		if(aLargerNode.mNodeType == MULTI_NODE){

			if(aSmallerNode.mNodeType == MONO_NODE){

				ans = mMultiMono.mExe(aLargerNode, aSmallerNode);

			}else
			if(aSmallerNode.mNodeType == MULTI_NODE){
				
				ans = mMultiMulti.mExe(aLargerNode, aSmallerNode);
				
			}else{
				throw new Exception("not implemented.");
			}
		
		}else{
			throw new Exception("not implemented.");
		}
		
		return ans;
	}
}
