package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import static mori.Lie.NodeTools.Holder.mMultiplier;
import static mori.Lie.NodeTools.Holder.mSignReverser;
import mori.Lie.Node;

public class BracketExpander {
	
	public Node mExe(Node arg)throws Exception{
		
		if(arg.mNodeType != BRACKET_NODE){
			
			throw new Exception();
		}
		
		Node node0 = arg.mGetSubNode(0);
		
		Node node1 = arg.mGetSubNode(1);
				
		Node first = new Node();
		
		Node second = new Node();
		
		mMultiplier.mExe(first,  node0, node1);
		
		mMultiplier.mExe(second, node1, node0);
		
		mSignReverser.mExe(second);
		
		Node ans = new Node();

		ans.mNodeType = POLYNOMIAL_NODE;
		
		ans.add(first);
		
		ans.add(second);
		
		return ans;
	}
}
