package mori.Lie.factor;

import static mori.Lie.factor.Intersector.*;
import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import static mori.Lie.node.tools.Holder.mEqualNomialChecker;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.translator.Holder.mNodeTranslator;
import static mori.tools.Holder.mArithmetic;
import static mori.tools.Holder.mGreatestCommonNumber;
import mori.Lie.Node;
import mori.tools.Rational;

public class IntersectorMultiMulti {

	public Node mExe(
			Node oneNode,
			Node anoNode,
			int  aLeftRight
	) throws Exception{
		Node destNode = new Node();

		destNode.mNodeType = MULTI_NODE;
		
		destNode.mCoef = mGreatestCommonNumber.mExe(
				oneNode.mCoef, 
				anoNode.mCoef);	
				
		int sign = 0;
		
		if(aLeftRight == INTERSECTOR_LEFT){
			
			sign = 1;
			
		}else if(aLeftRight == INTERSECTOR_RIGHT){
			
			sign = -1;
			
		}else{
		
			throw new Exception();
		}

		for(int id = 0;
		(sign == 1 && id < oneNode.mSubNodes.size() && id < anoNode.mSubNodes.size())
		||
		(sign == -1 && oneNode.mSubNodes.size()-1 -id >= 0 && anoNode.mSubNodes.size()-1 -id >= 0);
		id++){
			
			Node oneSubNode = null;
			Node anoSubNode = null;
			
			if(sign == -1){
				oneSubNode = oneNode.mGetSubNode(oneNode.mSubNodes.size() -1 - id);
				
				anoSubNode = anoNode.mGetSubNode(anoNode.mSubNodes.size() -1 - id);
			}else{
				oneSubNode = oneNode.mGetSubNode(id);
				
				anoSubNode = anoNode.mGetSubNode(id);
			}
			
			boolean equalFlag = mEqualNomialChecker.mExe(oneSubNode, anoSubNode);
						
			if(equalFlag){
				
				destNode.add( mFactory.mExe(oneSubNode) );
				
			}else{
				
				if(oneSubNode.mToken.equals(anoSubNode.mToken)){

					Node node = new Node();
					
					node.mNodeType = MONO_NODE;

					node.mToken = new String( oneSubNode.mToken );
					
					if(oneSubNode.mPower.mGreater(0) && anoSubNode.mPower.mGreater(0)){
						
						if(oneSubNode.mPower.mLesser(anoSubNode.mPower)){
							
							node.mPower = new Rational(oneSubNode.mPower);
							
							destNode.add(node);
							
						}else
						if(oneSubNode.mPower.mGreater(anoSubNode.mPower)){

							node.mPower = new Rational(anoSubNode.mPower);

							destNode.add(node);
							
						}else{
							throw new Exception();
						}
					}else
					if(oneSubNode.mPower.mLesser(0) 
					&& anoSubNode.mPower.mLesser(0)){
						
						if(oneSubNode.mPower.mLesser(anoSubNode.mPower)){

							node.mPower = new Rational(anoSubNode.mPower);
							
							destNode.add(node);
							
						}else
						if(oneSubNode.mPower.mGreater(anoSubNode.mPower)){

							node.mPower = new Rational(oneSubNode.mPower);
							
							destNode.add(node);
							
						}else{
							throw new Exception();
						}
					}else{
						// nothing to do.
					}
				}

				break;
			}
		}
		
		Node ans = mNodeTranslator.mExe(destNode);
		
		if(ans.mNodeType == NUMBER_NODE
		&& mArithmetic.mIsOne(ans.mCoef)){
			
			return null;
		
		}else{
			return ans;
		}
	}
}
