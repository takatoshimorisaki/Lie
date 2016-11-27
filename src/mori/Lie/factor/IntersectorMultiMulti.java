package mori.Lie.factor;

import static mori.Lie.factor.Holder.mIntersector;
import static mori.Lie.include.Holder.mIncludeChecker;
import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.translator.Holder.mNodeTranslator;
import static mori.tools.Holder.mGreatestCommonNumber;
import mori.Lie.Node;

public class IntersectorMultiMulti {

	public Node mExe(
			Node oneNode,
			Node anoNode
	) throws Exception{
		Node ans = new Node();

		int pos = mIncludeChecker.mExe(oneNode, anoNode);
		
		if(pos >= 0){
			
			ans.mNodeType = MULTI_NODE;
			
			ans = mFactory.mExe(anoNode);
			
		}else{
			
			pos = mIncludeChecker.mExe(anoNode, oneNode);
			
			if(pos >= 0){

				ans.mNodeType = MULTI_NODE;
				
				ans = mFactory.mExe(oneNode);
				
			}else{

				ans.mNodeType = MULTI_NODE;
				
				for(int oct = 0; oct < oneNode.mSubNodes.size(); oct++){
					
					Node oneSubNode = oneNode.mGetSubNode(oct);
					
					for(int act = 0; act < anoNode.mSubNodes.size(); act++){
					
						Node anoSubNode = anoNode.mGetSubNode(act);
						
						Node intersectNode = mIntersector.mExe(oneSubNode, anoSubNode);
						
						if(intersectNode.mNodeType == MONO_NODE){
							
							ans.add(intersectNode);
						}
					}
				}
				
				Node destNode = new Node();
				
				mNodeTranslator.mExe(destNode, ans);
				
				ans = mFactory.mExe(destNode);
			}
		}

		ans.mCoef = mGreatestCommonNumber.mExe(
				oneNode.mCoef, 
				anoNode.mCoef);	
				
		return ans;
	}
}
