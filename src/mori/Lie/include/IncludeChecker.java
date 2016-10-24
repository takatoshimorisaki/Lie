package mori.Lie.include;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class IncludeChecker {

	private static IncludeCheckerMonoMono mMonoMono = new IncludeCheckerMonoMono();
	
	private static IncludeCheckerMultiMono mMultiMono = new IncludeCheckerMultiMono();
	
	private static IncludeCheckerMultiMulti mMultiMulti = new IncludeCheckerMultiMulti();
	
	private static IncludeCheckerPoly mPoly = new IncludeCheckerPoly();
	
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
		}else
		if(aLargerNode.mNodeType == POLY_NODE){

			if(aSmallerNode.mNodeType == MONO_NODE
			|| aSmallerNode.mNodeType == MULTI_NODE){

				ans = mPoly.mExe(aLargerNode, aSmallerNode, this);
				
			}else
			if(aSmallerNode.mNodeType == POLY_NODE){
				
				throw new Exception("not implemented.");
				
			}else{
				throw new Exception("not implemented.");
			}
		}else{
			throw new Exception("not implemented.");
		}
		
		return ans;
	}
}
