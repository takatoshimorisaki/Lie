package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;

import mori.Lie.I_Operator;
import mori.tools.Rational;

import mori.Lie.Node;

public class Power implements I_Operator{

	public Node mExe(
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		return mExe(aOneNode, aAnoNode.mPower);
	}
	
	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		Node ans = new Node();
		
		ans = mExe(aOneNode, aAnoNode.mPower);
		
		mFactory.mCopy(aDestNode, ans);
	}
	
	public Node mExe(Node aNode, Rational aRational)throws Exception{
		
		Node ans = mFactory.mExe(aNode);
	
		ans.mPower = ans.mPower.mMultiply(aRational);
		
		return ans;
	}
}
