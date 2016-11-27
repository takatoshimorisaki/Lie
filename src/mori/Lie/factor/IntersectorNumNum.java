package mori.Lie.factor;

import static mori.Lie.Node.*;
import static mori.tools.Holder.mGreatestCommonNumber;
import mori.Lie.Node;

public class IntersectorNumNum {

	public Node mExe(
			Node oneNode,
			Node anoNode
	) throws Exception{
		Node ans = new Node();
		
		ans.mNodeType = NUMBER_NODE;
		
		ans.mCoef = mGreatestCommonNumber.mExe(
				oneNode.mCoef, 
				anoNode.mCoef);
		
		return ans;
	}
}
