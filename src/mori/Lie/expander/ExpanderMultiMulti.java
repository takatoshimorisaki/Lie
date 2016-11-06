package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.include.Holder.mIncludeChecker;
import static mori.Lie.node.tools.Holder.mFactory;
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

		out.printf("ExpanderMultiMulti arg %s leftNode %s rightNode %s\n",
				arg.toString(),
				leftNode.toString(),
				rightNode.toString());
		
		mFactory.mCopy(aDestNode, arg);
		
		while(startPos < arg.mSubNodes.size()){
			Node leftSubNode = leftNode.mGetSubNode(0);
	
			for(int d_id = (startPos + 1); d_id < aDestNode.mSubNodes.size(); d_id++){
				
				Node destSubNode = aDestNode.mGetSubNode(d_id);
				
				startPos = mIncludeChecker.mExe(destSubNode, leftSubNode);
				
				if(startPos >= 0){

					out.printf("startPos %d destSubNode %s leftSubNode %s\n", 
							startPos, 
							destSubNode.toString(),
							leftSubNode.toString());
					
					break;
				}
			}
			
			if(startPos == -1){
				return expanded;
			}
			
			if(leftNode.mSubNodes.size() > (aDestNode.mSubNodes.size() - startPos)){
				
				return expanded;
			}
		
			Node removedNode = new Node();
			
			Node[] splitedNode = mRemover.mExe(removedNode, aDestNode, leftNode, startPos);

			mMultiplier.mExe(aDestNode, splitedNode[0], rightNode);
			
			mMultiplier.mExe(aDestNode, aDestNode, splitedNode[1]);
					
			expanded = true;
			
			startPos++;
			
			out.printf("startPos %d\n", startPos);
		}
		
		return expanded;
	}
}
