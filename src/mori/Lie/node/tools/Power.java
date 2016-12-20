package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import mori.tools.Rational;

import mori.Lie.Node;

public class Power {

	public Node mExe(Node aNode, Rational aRational)throws Exception{
		
		Node ans = mFactory.mExe(aNode);
	
		ans.mPower = ans.mPower.mMultiply(aRational);
		
		return ans;
	}
}
