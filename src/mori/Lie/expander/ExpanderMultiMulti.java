package mori.Lie.expander;

import mori.Lie.Node;
import mori.Lie.NodeTools.Factory;
import mori.Lie.NodeTools.IncludeChecker;
import mori.Lie.NodeTools.EqualNomialChecker;
import mori.Lie.NodeTools.Remover;
import mori.Lie.NodeTools.Multiplier;

public class ExpanderMultiMulti {

	private static Factory mFactory = new Factory();
	
	private static IncludeChecker mIncludeChecker = new IncludeChecker();
	
	private static EqualNomialChecker mEqualNomialChecker = new EqualNomialChecker();
	
	private static Remover mRemover = new Remover();
	
	private static Multiplier mMultiplier = new Multiplier();
	
	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node rightNode,
		Node leftNode
	)throws Exception{
		boolean expanded = false;
		int     startPos = -1;
		
		while(startPos < arg.mSubNodes.size()){
			Node rightSubNode = rightNode.mGetSubNode(0);
	
			for(int d_id = (startPos + 1); d_id < arg.mSubNodes.size(); d_id++){
				
				Node argSubNode = aDestNode.mGetSubNode(d_id);
				
				boolean includes = mIncludeChecker.mExe(argSubNode, rightSubNode);
				
				if(includes){
					startPos = d_id;
					
					break;
				}
			}
			
			if(startPos == -1){
				return expanded;
			}
			
			if(rightNode.mSubNodes.size() < (arg.mSubNodes.size() - startPos)){
				
				return expanded;
			}
			
			for(int r_id = 1; r_id < rightNode.mSubNodes.size(); r_id++){
				
				rightSubNode = rightNode.mGetSubNode(r_id);
				
				int d_id = (startPos + r_id);
				
				Node argSubNode = arg.mGetSubNode(d_id);
				
				boolean equals = mEqualNomialChecker.mExe(rightSubNode, argSubNode);
				
				if(equals == false){
					break;
				}
			}
			
			startPos++;
		}
		
		Node removedNode = new Node();
		
		mRemover.mExe(removedNode, arg, rightNode, startPos);
	
		mFactory.mCopy(aDestNode, arg.mGetSubNode(0));
		
		for(int id = 1; id < startPos; id++){
			
			Node argSubNode = arg.mGetSubNode(id);
			
			aDestNode = mMultiplier.mExe(aDestNode, argSubNode);
		}

		aDestNode = mMultiplier.mExe(aDestNode, removedNode);

		for(int id = startPos + rightNode.mSubNodes.size();
				id < arg.mSubNodes.size();
				id++){

			Node argSubNode = arg.mGetSubNode(id);
			
			aDestNode = mMultiplier.mExe(aDestNode, argSubNode);
		}
		
		expanded = true;
		
		return expanded;
	}
}
