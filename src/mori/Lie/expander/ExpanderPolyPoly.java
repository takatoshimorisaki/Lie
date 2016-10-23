package mori.Lie.expander;

import mori.Lie.adder.Adder;
import mori.Lie.include.IncludeChecker;
import mori.Lie.Node;
import mori.Lie.NodeTools.Subtracter;

public class ExpanderPolyPoly {
	
	private static Adder mAdder = new Adder();
	
	private static IncludeChecker mIncludeChecker = new IncludeChecker();
	
	private static Subtracter mSubtracter = new Subtracter();
	
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
