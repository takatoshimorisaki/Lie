package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import mori.Lie.Node;

public class ParenthesisWrapper {

	public Node mExe(Node arg)throws Exception{
		Node ans = new Node();
		
		ans.mNodeType = PARENTHESIS_NODE;
		
		ans.add( mFactory.mExe(arg) );
		
		return ans;
	}
}
