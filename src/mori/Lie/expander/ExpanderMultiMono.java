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
		Node rightNode,
		Node leftNode
	)throws Exception{
		boolean expanded = false;

		mFactory.mCopy(aDestNode, arg);
		
		int  replacedId = -1;
		Node newNode    = null;
		
		do{
			for(int id = 0; id < aDestNode.mSubNodes.size(); id++){
				
				Node node = aDestNode.mGetSubNode(id);
				
				newNode = new Node();
				
				boolean rtn = mExpanderMono.mExe(newNode, node, rightNode, leftNode);
				
				if(rtn = true){
	
					replacedId = id;
					
					expanded = true;
					
					break;
				}
			}// for id

			if(replacedId >= 0){
				Node[] splitedNode = mNodeSplitter.mExe(replacedId, arg);
			
				if(splitedNode[0] != null){
					aDestNode = mMultiplier.mExe(splitedNode[0], newNode);
					
					if(splitedNode[1] != null){
						aDestNode = mMultiplier.mExe(aDestNode, splitedNode[1]);
					}
				}else{
					aDestNode = newNode;
	
					if(splitedNode[1] != null){
						aDestNode = mMultiplier.mExe(aDestNode, splitedNode[1]);
					}
				}
			}
		}while(replacedId >= 0);
		
		return expanded;
	}
}
