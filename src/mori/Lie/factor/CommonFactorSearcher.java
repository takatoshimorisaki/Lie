package mori.Lie.factor;

import static mori.Lie.factor.Holder.mIntersector;
import static mori.Lie.Node.*;
import static  mori.Lie.node.tools.Holder.mNodeSplitter;
import mori.Lie.Node;

public class CommonFactorSearcher {

	public static Node mExe(
			Node arg
	)throws Exception{
		
		if(arg.mNodeType == NULL_NODE
		|| arg.mNodeType == NUMBER_NODE
		|| arg.mNodeType == MONO_NODE
		|| arg.mNodeType == MULTI_NODE
		|| arg.mNodeType == EQU_NODE
		|| arg.mNodeType == OPE_ADD_NODE
		|| arg.mNodeType == OPE_SUB_NODE
		|| arg.mNodeType == OPE_MULTI_NODE){
			
			return null;
			
		}else
		if(arg.mNodeType == POLY_NODE){
			
			Node[] parts = mNodeSplitter.mExe(arg);
			
			Node ans = mIntersector.mExe(parts[0], parts[1]);
			
			for(int id = 2; id < parts.length; id++){
				
				ans = mIntersector.mExe(ans, parts[id]);
			}
			
			return ans;
			
		}else{
		
			throw new Exception("not implemented.");
		}
	}
}
