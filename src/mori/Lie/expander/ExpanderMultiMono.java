package mori.Lie.expander;

import mori.Lie.Node;
import mori.Lie.NodeTools.Factory;
import mori.Lie.NodeTools.Multiplier;
import mori.Lie.NodeTools.NodeSplitter;

public class ExpanderMultiMono {

	private static Factory mFactory = new Factory();
	
	private static Multiplier mMultiplier = new Multiplier();
	
	private static ExpanderMono mExpanderMono = new ExpanderMono();
	
	private static NodeSplitter mNodeSplitter = new NodeSplitter();
	
	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node leftNode,
		Node rightNode
	)throws Exception{
		boolean expanded = false;

		mFactory.mCopy(aDestNode, arg);
		
		int  replacedId = -1;
		Node newNode    = null;
		
		do{
			for(int id = 0; id < aDestNode.mSubNodes.size(); id++){
				
				Node node = aDestNode.mGetSubNode(id);
				
				newNode = new Node();
				
				boolean rtn = mExpanderMono.mExe(newNode, node, leftNode, rightNode);
				
				if(rtn = true){
	
					replacedId = id;
					
					expanded = true;
					
					break;
				}
			}// for id

		}while(replacedId >= 0);
		
		return expanded;
	}
}
