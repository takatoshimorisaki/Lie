package mori.Lie.adder;

import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.Lie.NodeTools.EqualChecker;
import mori.Lie.NodeTools.Factory;

public class AdderBraBra {

	private static EqualChecker mEqualChecker = new EqualChecker();

	private static Factory mFactory = new Factory();
	
	public Node mExe(
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		Node ans = new Node();
		
		mExe(ans, aOneNode, aAnoNode);
		
		return ans;
	}

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		if(mEqualChecker.mExe(aOneNode, aAnoNode)){
			
			mFactory.mCopy(aDestNode, aOneNode);
			
			aDestNode.mCoef += aAnoNode.mCoef;
			
		}else{
			
			aDestNode.mNodeType = POLY_NODE;
			
			aDestNode.add(aOneNode);
			
			aDestNode.add(aAnoNode);
		}
	}
}
