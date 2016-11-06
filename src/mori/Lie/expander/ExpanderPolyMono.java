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

		out.printf("ExpanderPolyMono arg %s leftNode %s rightNode %s\n",
				arg.toString(),
				leftNode.toString(),
				rightNode.toString());
		
		int size = aDestNode.mSubNodes.size();
		
		for(int id = 0; id < size; id++){
			
			Node node = aDestNode.mGetSubNode(id);
			
			Node expandedNode = new Node();
			
			if(aExpander.mExe(expandedNode, node, leftNode, rightNode)){
		
				out.printf("ExpanderPolyMono2 expandedNode %s node %s leftNode %s rightNode %s\n",
						expandedNode.toString(),
						node.toString(),
						leftNode.toString(),
						rightNode.toString());
				
				Node[] splitNode = mNodeSplitter.mExe(id, aDestNode);
				
				out.printf("ExpanderPolyMono3 id %d aDestNode %s\n",
						id,
						aDestNode.toString());
				
				if(splitNode[0] != null){
					
					mAdder.mExe(aDestNode, splitNode[0], expandedNode);
			
					out.printf("ExpanderPolyMono4 aDestNode %s splitNode[0] %s expandedNode %s\n",
							aDestNode.toString(),
							splitNode[0].toString(),
							expandedNode.toString());
					
					if(splitNode[1] != null){
						
						mAdder.mExe(aDestNode, aDestNode, splitNode[1]);
						
						out.printf("ExpanderPolyMono5 aDestNode %s splitNode[1] %s\n",
								aDestNode.toString(),
								splitNode[1].toString());
					}
				}else{
					
					mAdder.mExe(aDestNode, expandedNode, splitNode[1]);

					out.printf("ExpanderPolyMono6 aDestNode %s expandedNode %s splitNode[1] %s\n",
							aDestNode.toString(),
							expandedNode.toString(),
							splitNode[1].toString());
				}
				
				expanded = true;
				
				break;
			}
		}
		
		return expanded;
	}
}
