package mori.Lie.expander;

import static mori.Lie.include.Holder.mIncludeChecker;
import static mori.Lie.node.tools.Holder.mMultiplier;
import static mori.Lie.node.tools.Holder.mRemover;

import mori.Lie.Node;

public class ExpanderMultiMulti {

	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node leftNode,
		Node rightNode
	)throws Exception{
		boolean expanded = false;
		int     startPos = -1;
		
		while(startPos < arg.mSubNodes.size()){
			Node leftSubNode = leftNode.mGetSubNode(0);
	
			for(int d_id = (startPos + 1); d_id < arg.mSubNodes.size(); d_id++){
				
				Node argSubNode = arg.mGetSubNode(d_id);
				
				startPos = mIncludeChecker.mExe(argSubNode, leftSubNode);
				
				if(startPos >= 0){
					
					break;
				}
			}
			
			if(startPos == -1){
				return expanded;
			}
			
			if(leftNode.mSubNodes.size() > (arg.mSubNodes.size() - startPos)){
				
				return expanded;
			}
		
			Node removedNode = new Node();
			
			Node[] splitedNode = mRemover.mExe(removedNode, arg, leftNode, startPos);

			aDestNode = mMultiplier.mExe(splitedNode[0], rightNode);
			
			aDestNode = mMultiplier.mExe(aDestNode, splitedNode[1]);
					
			expanded = true;
			
			startPos++;
		}
		
		return expanded;
	}
}
