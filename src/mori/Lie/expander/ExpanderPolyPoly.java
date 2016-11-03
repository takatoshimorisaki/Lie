package mori.Lie.expander;

import static mori.Lie.adder.Holder.mAdder;
import static mori.Lie.include.Holder.mIncludeChecker;
import static mori.Lie.node.tools.Holder.mSubtracter;

import mori.Lie.Node;

public class ExpanderPolyPoly {
	
	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node leftNode,
		Node rightNode
	)throws Exception{
		boolean expanded = false;

		int pos = mIncludeChecker.mExe(arg, leftNode);
		
		if(pos >= 0){
		
			mSubtracter.mExe(aDestNode, arg, leftNode);
			
			mAdder.mExe(aDestNode, aDestNode, rightNode);
			
			expanded = true;
		}
		
		return expanded;
	}
}
