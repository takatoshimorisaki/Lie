package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.adder.Holder.mAdder;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mNodeSplitter;

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

		mFactory.mCopy(aDestNode, arg);
		
		int size = aDestNode.mSubNodes.size();
		
		for(int id = 0; id < size; id++){
			
			Node node = aDestNode.mGetSubNode(id);
			
			Node expandedNode = new Node();
			
			if(aExpander.mExe(expandedNode, node, leftNode, rightNode)){
				
				Node[] splitNode = mNodeSplitter.mExe(id, aDestNode);
				
				if(splitNode[0] != null){
					
					mAdder.mExe(aDestNode, splitNode[0], expandedNode);
				}
				
				if(splitNode[1] != null){
				
					if(splitNode[0] != null){

						mAdder.mExe(aDestNode, aDestNode, splitNode[1]);
						
					}else{

						mAdder.mExe(aDestNode, expandedNode, splitNode[1]);
					}
				}
				
				expanded = true;
				
				break;
			}
		}
		
		return expanded;
	}
}
