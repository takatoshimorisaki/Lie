package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.include.Holder.mIncludeChecker;
import static mori.Lie.Node.*;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.node.tools.Holder.mFactory;
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

				out.printf("ExpanderMultiMulti startPos %d aDestNode %s leftNode %s\n", 
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

			if(splitedNode[0] != null){
				mMultiplier.mExe(aDestNode, splitedNode[0], rightNode);
			}else{
				mFactory.mCopy(aDestNode, rightNode);
			}
			
			if(splitedNode[1] != null){
								
				Node node = new Node();

				out.printf("aDestNode %s\n", aDestNode.toString());
				
				out.printf("splitedNode[1] %s\n", splitedNode[1].toString());
				
				mMultiplier.mExe(node, aDestNode, splitedNode[1]);

				mFactory.mCopy(aDestNode, node);
			}
			
			expanded = true;
			
			startPos++;
		}
		
		return expanded;
	}
	
	public static void main(String[] args){
		try{
			ExpanderMultiMulti emm = new ExpanderMultiMulti();
			
			Node destNode = new Node();
			
			// arg
			
			Node arg = new Node();
			
			arg.mNodeType = MULTINOMIAL_NODE;
			
			arg.mCoef = -1.0;
			
			Node subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = "Y";
					
			arg.add(subNode);
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = "i";
			
			arg.add(subNode);
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = "Px";
			
			arg.add(subNode);

			// leftNode
			
			Node leftNode = new Node();
			
			leftNode.mNodeType = MULTINOMIAL_NODE;
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = "Y";
			
			leftNode.add(subNode);

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = "i";
			
			leftNode.add(subNode);
			
			// rightNode
			
			Node rightNode = new Node();
			
			rightNode.mNodeType = MULTINOMIAL_NODE;
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = "i";
			
			rightNode.add(subNode);

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mToken = "Y";
			
			rightNode.add(subNode);
			
			boolean rtn = emm.mExe(destNode, arg, leftNode, rightNode);
			
			out.printf("rtn %b\n", rtn);
			out.printf("destNode %s\n", destNode.toString());
			out.printf("arg %s\n",  arg.toString());
			out.printf("leftNode %s\n", leftNode.toString());
			out.printf("rightNode %s\n", rightNode.toString());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
