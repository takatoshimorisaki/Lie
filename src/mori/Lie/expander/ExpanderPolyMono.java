package mori.Lie.expander;

import mori.Lie.Node;

public class ExpanderPolyMono {

	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node leftNode,
		Node rightNode,
		Expander aExpander
	)throws Exception{
		boolean expanded = false;

		int size = arg.mSubNodes.size();
		
		for(int id = 0; id < size; id++){
			
			Node node = arg.mGetSubNode(id);
			
			if(aExpander.mExe(aDestNode, node, leftNode, rightNode)){
				
				arg.mSubNodes.set(id, aDestNode);
				
				expanded = true;
			}
		}
		
		return expanded;
	}
}
