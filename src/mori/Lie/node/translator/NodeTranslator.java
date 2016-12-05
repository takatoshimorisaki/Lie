package mori.Lie.node.translator;

import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.translator.Holder.mMultiMonoTranslator;
import static mori.Lie.node.translator.Holder.mOpeNodeTranslator;
import static mori.Lie.node.translator.Holder.mParenthesisTranslator;
import mori.Lie.Node;

public class NodeTranslator {

	public Node mExe(
		Node aSrcNode
	)throws Exception{
		Node ans = new Node();
	
		mExe(ans, aSrcNode);
		
		return ans;
	}
	
	public void mExe(
		Node aDestNode,
		Node aSrcNode
	)throws Exception{

		mFactory.mCopy(aDestNode, aSrcNode);

		if(aSrcNode.mNodeType == NULL_NODE
		|| aSrcNode.mNodeType == NUMBER_NODE){
			// nothing to do.
		}else
		if(aSrcNode.mNodeType == MONO_NODE){
			
			if(aSrcNode.mToken == null){
				
				aDestNode.mPower = 1;
				
				aDestNode.mNodeType = NUMBER_NODE;
			}
		}else
		if(aSrcNode.mNodeType == MULTI_NODE){
			
			if(aDestNode.mSubNodes.size() == 0){
			
				aDestNode.mNodeType = NUMBER_NODE;
						
			}else
			if(aDestNode.mSubNodes.size() == 1){
				
				mMultiMonoTranslator.mExe(aDestNode, aSrcNode);
				
			}else{
			
				aDestNode.mNodeType = MULTI_NODE;
			}
		}else
		if(aSrcNode.mNodeType == PARENTHESIS_NODE){
			
			mParenthesisTranslator.mExe(aDestNode, aSrcNode);
			
		}else
		if(aSrcNode.mNodeType == EQU_NODE){
				
			Node leftNode = aDestNode.mGetSubNode(0);
			
			Node rightNode = aDestNode.mGetSubNode(1);
			
			leftNode = this.mExe(leftNode);
			
			rightNode = this.mExe(rightNode);
			
			aDestNode.mSet(0, leftNode);
			
			aDestNode.mSet(1, rightNode);
			
		}else
		if(aSrcNode.mNodeType == OPE_ADD_NODE
		|| aSrcNode.mNodeType == OPE_SUB_NODE
		|| aSrcNode.mNodeType == OPE_MULTI_NODE){
			
			mOpeNodeTranslator.mExe(aDestNode, aSrcNode);
			
		}else{
			// nothing to do.
		}
	}
}
