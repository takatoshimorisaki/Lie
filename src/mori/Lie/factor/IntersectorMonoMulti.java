package mori.Lie.factor;

import static mori.Lie.factor.Holder.mIntersectorMonoMono;
import static mori.Lie.factor.Intersector.*;
import static mori.Lie.Node.*;
import static mori.tools.Holder.mGreatestCommonNumber;
import mori.Lie.Node;

public class IntersectorMonoMulti {

	public Node mExe(
			Node oneNode,
			Node anoNode,
			int  aLeftRight
	) throws Exception{
		Node ans = new Node();

		ans.mCoef = mGreatestCommonNumber.mExe(
				oneNode.mCoef, 
				anoNode.mCoef);
		
		Node node = null;
		
		if(aLeftRight == INTERSECTOR_LEFT){

			Node subNode = anoNode.mGetSubNode(0);

			node = mIntersectorMonoMono.mExe(oneNode, subNode);
			
		}else if(aLeftRight == INTERSECTOR_RIGHT){

			Node subNode = anoNode.mGetSubNode(anoNode.mSubNodes.size() - 1);

			node = mIntersectorMonoMono.mExe(oneNode, subNode);
			
		}else{
			
			throw new Exception();
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
