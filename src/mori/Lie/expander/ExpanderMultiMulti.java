package mori.Lie.expander;

import static java.lang.System.out;
import static mori.Lie.include.Holder.mIncludeChecker;
import static mori.Lie.Node.*;
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

			out.printf("ExpanderMultiMulti10 removedNode %s\n", removedNode.toString());
			
			if(splitedNode[0] != null){
				mMultiplier.mExe(aDestNode, splitedNode[0], rightNode);
				
				out.printf("ExpanderMultiMulti2 aDestNode %s splitedNode[0] %s rightNode %s\n",
						aDestNode.toString(),
						splitedNode[0].toString(),
						rightNode.toString());
			}else{
				mFactory.mCopy(aDestNode, rightNode);
				
				out.printf("ExpanderMultiMulti3 aDestNode %s rightNode %s\n",
						aDestNode.toString(),
						rightNode.toString());
			}
			
			if(splitedNode[1] != null){
				
				Node node = new Node();
				
				mMultiplier.mExe(node, aDestNode, splitedNode[1]);
				
				mFactory.mCopy(aDestNode, node);
				
				out.printf("ExpanderMultiMulti4 aDestNode %s splitedNode[1] %s\n",
						aDestNode.toString(),
						splitedNode[1].toString());
			}
			
			expanded = true;
			
			startPos++;
			
			out.printf("startPos %d\n", startPos);
		}
		
		return expanded;
	}
	
	public static void main(String[] args){
		try{
			ExpanderMultiMulti emm = new ExpanderMultiMulti();
			
			Node destNode = new Node();
			
			Node arg = new Node();
			
			arg.mNodeType = MULTINOMIAL_NODE;
			
			arg.mCoef = -1.0;
			
			Node subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Y";
			
			subNode.mPower = 1;
			
			arg.add(subNode);
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Pz";
			
			subNode.mPower = 1;
			
			arg.add(subNode);
			
			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "X";
			
			subNode.mPower = 1;
			
			arg.add(subNode);

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Pz";
			
			subNode.mPower = 1;
			
			arg.add(subNode);
			
			Node leftNode = new Node();
			
			leftNode.mNodeType = MULTINOMIAL_NODE;
			
			leftNode.mCoef = 1.0;

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Pz";
			
			subNode.mPower = 1;
			
			leftNode.add(subNode);

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "X";
			
			subNode.mPower = 1;
			
			leftNode.add(subNode);
			
			Node rightNode = new Node();
			
			rightNode.mNodeType = MULTINOMIAL_NODE;
			
			rightNode.mCoef = 1.0;

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "X";
			
			subNode.mPower = 1;
			
			rightNode.add(subNode);

			subNode = new Node();
			
			subNode.mNodeType = MONOMIAL_NODE;
			
			subNode.mCoef = 1.0;
			
			subNode.mToken = "Pz";
			
			subNode.mPower = 1;
			
			rightNode.add(subNode);
			
			boolean rtn = emm.mExe(destNode, arg, leftNode, rightNode);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
