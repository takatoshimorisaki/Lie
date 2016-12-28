package mori.Lie.node.parser;

import static java.lang.System.out;
import static mori.Lie.adder.Holder.mAdder;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import static mori.Lie.node.tools.Holder.mDivider;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mPower;
import static mori.Lie.node.tools.Holder.mSubtracter;
import mori.Lie.adder.Adder;
import mori.Lie.I_Operator;
import mori.Lie.Node;
import mori.Lie.node.tools.Subtracter;
import mori.tools.Rational;

public class NodeParser {

	private Node mNode0;
	
	private Node mNode1;

	private Node mLeftNode;
	
	private Node mRightNode;
	
	private I_Operator mOperator;
		
	private Rational mRational;
	
	public void mPush(Node arg)throws Exception{
	
		out.printf("NodeParser mPush %s\n", arg.mToString());
		
		mNode0 = arg;
		
		if(mNode1 == null){
			// nothing to do.
		}else
		if(mNode1.mNodeType == BRACKET_START_NODE){

			mLeftNode = mFactory.mExe(mNode0);
			
		}else
		if(mNode1.mNodeType == COMMA_NODE){

			mRightNode = mFactory.mExe(mNode0);

		}else
		if(mNode0.mNodeType == BRACKET_END_NODE){
			
			mNode0.mNodeType = BRACKET_NODE;
			
			mNode0.mSubNodes = new java.util.Vector<Node>();
			
			mNode0.add(mLeftNode);
			
			mNode0.add(mRightNode);

		}else
		if(NodeTypeChecker.mIsNomialNode(mNode1)){
			
			if(mOperator != null){
			
				if(NodeTypeChecker.mIsNomialNode(mNode0)){
					
					if(mNode1.mSubNodes.size() == 0){

						mNode0 = mOperator.mExe(mNode1, mNode0);
					
					}else{
		
						if(mOperator instanceof Adder
						|| mOperator instanceof Subtracter){

							mNode1 = mOperator.mExe(mNode1, mNode0);
							
						}else{
							Node node = mNode1.mGetSubNode(mNode1.mSubNodes.size() - 1);
	
							Node dest = mOperator.mExe(node, mNode0);

							mFactory.mCopy(node, dest);
						}
						
						mNode0 = mFactory.mExe(mNode1);
					}
					
					mOperator = null;

				}else{
					throw new Exception();
				}
			}else
			if(NodeTypeChecker.mIsNomialNode(mNode0)){

				mNode0 = mMultiplier.mExe(mNode1, mNode0);

			}else
			if(NodeTypeChecker.mIsOperatorNode(mNode0)){
				
				mOperator = mFactory.mGetOperator(mNode0);
				
			}else
			if(mNode0.mNodeType == COMMA_NODE){
				// NOTHING TO DO.
			}else{
				throw new Exception(mNode0.toNodeType());
			}
		}

		if(mOperator == null){
			mNode1 = mFactory.mExe(mNode0);
		}
		
		out.printf("NodeParser end %s\n", mNode0.mToString());
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
