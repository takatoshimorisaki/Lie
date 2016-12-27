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
import mori.Lie.I_Operator;
import mori.Lie.Node;
import mori.tools.Rational;

public class NodeParser {

	private Node mNode0;
	
	private Node mNode1;

	private Node mLeftNode;
	
	private Node mRightNode;
	
	private I_Operator mOperator;
		
	private Rational mRational;
	
	public void mPush(Node arg)throws Exception{
	
		mNode0 = arg;
		
		if(mNode1 == null){
			// nothing to do.
			
			out.printf("NodeParser mNode1 == null\n");
			
		}else
		if(mNode1.mNodeType == BRACKET_START_NODE){

			mLeftNode = mFactory.mExe(mNode0);
			
			out.printf("NodeParser BRACKET_START_NODE %s\n", mLeftNode.mToString());
			
		}else
		if(mNode1.mNodeType == COMMA_NODE){

			mRightNode = mFactory.mExe(mNode0);

			out.printf("NodeParser COMMA_NODE %s\n", mRightNode.mToString());
			
		}else
		if(mNode0.mNodeType == BRACKET_END_NODE){
			
			mNode0.mNodeType = BRACKET_NODE;
			
			mNode0.mSubNodes = new java.util.Vector<Node>();
			
			mNode0.add(mLeftNode);
			
			mNode0.add(mRightNode);

			out.printf("NodeParser BRACKET_END_NODE\n");
			
		}else
		if(NodeTypeChecker.mIsNomialNode(mNode1)){
			
			if(mOperator != null){
			
				if(NodeTypeChecker.mIsNomialNode(mNode0)){
					
					if(mNode1.mSubNodes.size() == 0){

						out.printf("NodeParser Before %s(%s,%d) %s(%s,%d)\n", 
								mNode1.mToString(), 
								mNode1.toNodeType(),
								mNode1.mSubNodes.size(),
								mNode0.mToString(),
								mNode0.toNodeType(),
								mNode0.mSubNodes.size());
						
						mNode0 = mOperator.mExe(mNode1, mNode0);
					
						out.printf("NodeParser After %s(%s,%d)\n", 
								mNode0.mToString(),
								mNode0.toNodeType(),
								mNode0.mSubNodes.size());
						
					}else{

						out.printf("NodeParser2 Before %s(%s,%d) %s(%s,%d)\n", 
								mNode1.mToString(), 
								mNode1.toNodeType(),
								mNode1.mSubNodes.size(),
								mNode0.mToString(),
								mNode0.toNodeType(),
								mNode0.mSubNodes.size());
											
						Node node = mNode1.mGetSubNode(mNode1.mSubNodes.size() - 1);

						node = mOperator.mExe(node, mNode0);

						out.printf("NodeParser2 mid %s(%s,%d)\n", 
								node.mToString(), 
								node.toNodeType(),
								node.mSubNodes.size());

						mNode1.mRemove(mNode1.mSubNodes.size() - 1);
							
						if(node.mNodeType == POLY_NODE){
							
							mNode1 = mAdder.mExe(mNode1, node);
							
						}else{
							
							mNode1 = mMultiplier.mExe(mNode1, node);

							out.printf("NodeParser2 After %s(%s,%d)\n", 
									mNode1.mToString(),
									mNode1.toNodeType(),
									mNode1.mSubNodes.size());
						}
						
						mNode0 = mFactory.mExe(mNode1);
					}
					
					mOperator = null;

				}else{
					throw new Exception();
				}
			}else
			if(NodeTypeChecker.mIsNomialNode(mNode0)){

				out.printf("NodeParser Before %s %s\n", mNode1.mToString(), mNode0.mToString());
				
				mNode0 = mMultiplier.mExe(mNode1, mNode0);

				out.printf("NodeParser After %s\n", mNode0.mToString());
				
			}else
			if(NodeTypeChecker.mIsOperatorNode(mNode0)){
				
				mOperator = mFactory.mGetOperator(mNode0);
				
				out.printf("Nodeparser mOperator %s\n", mOperator.mToString());
				
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
	}
	
	public Node mExe(Node arg)throws Exception{

		for(int id = 0; id < arg.mSubNodes.size(); id++){
			
			Node node = arg.mGetSubNode(id);
			
			out.printf("NodeParser mExe %d %s\n", id, node.mToString());
			
			if(node.mNodeType != NULL_NODE){
			
				mPush(node);
			}
		}
		
		return mNode0;
	}
}
