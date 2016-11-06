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

		mFactory.mCopy(aDestNode, arg);
		
		while(startPos < arg.mSubNodes.size()){

			startPos = mIncludeChecker.mExe(aDestNode, leftNode);
			
			if(startPos >= 0){

				out.printf("startPos %d aDestNode %s leftNode %s\n", 
						startPos, 
						aDestNode.toString(),
						leftNode.toString());
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
