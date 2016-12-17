package mori.Lie.node.parser;

import static mori.Lie.adder.Holder.mAdder;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mDivider;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mSubtracter;
import mori.Lie.Node;

public class NodeParser {

	private Node mNode0;
	
	private Node mNode1;
	
	private Node mOpeNode;
	
	public void mPush(Node arg)throws Exception{
	
		mNode0 = arg;
		
		if(mNode1 == null){

			mNode1 = mFactory.mExe(mNode0);
			
		}else
		if(mNode1.mNodeType == NUMBER_NODE){
			
			if(mOpeNode != null){
				
				if(mOpeNode.mNodeType == OPE_ADD_NODE){
					
					if(mNode0.mNodeType == NUMBER_NODE
					|| mNode0.mNodeType == MONO_NODE
					|| mNode0.mNodeType == MULTI_NODE
					|| mNode0.mNodeType == POLY_NODE
					|| mNode0.mNodeType == BRACKET_NODE){
						
						mNode0 = mAdder.mExe(mNode1, mNode0);

						mNode1 = mFactory.mExe(mNode0);
						
						mOpeNode = null;
						
					}else{
						throw new Exception();
					}
				}else
				if(mOpeNode.mNodeType == OPE_SUB_NODE){

					if(mNode0.mNodeType == NUMBER_NODE
					|| mNode0.mNodeType == MONO_NODE
					|| mNode0.mNodeType == MULTI_NODE
					|| mNode0.mNodeType == POLY_NODE
					|| mNode0.mNodeType == BRACKET_NODE){
						
						mNode0 = mSubtracter.mExe(mNode1, mNode0);

						mNode1 = mFactory.mExe(mNode0);
						
						mOpeNode = null;

					}else{
						throw new Exception();
					}	
				}else
				if(mOpeNode.mNodeType == OPE_MULTI_NODE){

					if(mNode0.mNodeType == NUMBER_NODE
					|| mNode0.mNodeType == MONO_NODE
					|| mNode0.mNodeType == MULTI_NODE
					|| mNode0.mNodeType == POLY_NODE
					|| mNode0.mNodeType == BRACKET_NODE){
						
						mNode0 = mMultiplier.mExe(mNode1, mNode0);

						mNode1 = mFactory.mExe(mNode0);
						
						mOpeNode = null;

					}else{
						throw new Exception();
					}
				}else
				if(mOpeNode.mNodeType == OPE_DIV_NODE){

					if(mNode0.mNodeType == NUMBER_NODE
					|| mNode0.mNodeType == MONO_NODE
					|| mNode0.mNodeType == MULTI_NODE
					|| mNode0.mNodeType == POLY_NODE
					|| mNode0.mNodeType == BRACKET_NODE){
						
						mNode0 = mDivider.mExe(mNode1, mNode0, mDivider.DIVIDER_RIGHT);

						mNode1 = mFactory.mExe(mNode0);
						
						mOpeNode = null;

					}else{
						throw new Exception();
					}
						
				}else{
					throw new Exception();
				}
			}else
			if(mNode0.mNodeType == NUMBER_NODE
			|| mNode0.mNodeType == MONO_NODE
			|| mNode0.mNodeType == MULTI_NODE
			|| mNode0.mNodeType == POLY_NODE
			|| mNode0.mNodeType == BRACKET_NODE){
				
				mNode0 = mMultiplier.mExe(mNode1, mNode0);

				mNode1 = mFactory.mExe(mNode0);
			
			}else
			if(mNode0.mNodeType == OPE_ADD_NODE
			|| mNode0.mNodeType == OPE_SUB_NODE
			|| mNode0.mNodeType == OPE_MULTI_NODE){
				
				mOpeNode = mFactory.mExe(mNode0);
				
			}else{
				throw new Exception();
			}
		}
		
	}
	
	public Node mExe(Node arg)throws Exception{

		for(int id = 0; id < arg.mSubNodes.size(); id++){
			
			Node node = arg.mGetSubNode(id);
			
			if(node.mNodeType != NULL_NODE){
			
				mPush(node);
			}
		}
		
		return mNode0;
	}
}
