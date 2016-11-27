package mori.Lie.factor;

import static mori.Lie.factor.Holder.mIntersectorMonoMono;
import static mori.Lie.Node.*;
import static mori.tools.Holder.mGreatestCommonNumber;
import mori.Lie.Node;

public class IntersectorMonoMulti {

	public Node mExe(
			Node oneNode,
			Node anoNode
	) throws Exception{
		Node ans = new Node();

		ans.mCoef = mGreatestCommonNumber.mExe(
				oneNode.mCoef, 
				anoNode.mCoef);
		
		Node node = null;
		
		for(int id = 0; id < anoNode.mSubNodes.size(); id++){
			
			Node subNode = anoNode.mGetSubNode(id);
			
			node = mIntersectorMonoMono.mExe(oneNode, subNode);
			
			if(node.mNodeType == MONO_NODE){
				
				break;
			}
		}

		if(node.mNodeType == MONO_NODE){

			ans.mNodeType = MONO_NODE;
			
			ans.mToken = new String( node.mToken );
			
			ans.mPower = node.mPower;
			
		}else{
		
			ans.mNodeType = NUMBER_NODE;
		}
		
		return ans;
	}
}
