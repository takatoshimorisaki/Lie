package mori.Lie.adder;

import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.Lie.node.tools.EqualNomialChecker;
import mori.Lie.node.tools.Factory;

public class AdderMultiMulti {

	private static EqualNomialChecker mEqualNomialChecker = new EqualNomialChecker();

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
		
		if(mEqualNomialChecker.mExe(aOneNode, aAnoNode)){
			
			mFactory.mCopy(aDestNode, aOneNode);
			
			aDestNode.mCoef += aAnoNode.mCoef;

			if(Math.abs(aDestNode.mCoef) < DOUBLE_THRESHOLD){
				
				aDestNode.mNodeType = NULL_NODE;
			}
		}else{
			
			aDestNode.mNodeType = POLY_NODE;
			
			aDestNode.add(aOneNode);
			
			aDestNode.add(aAnoNode);
		}
	}
}
