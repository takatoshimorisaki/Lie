package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class EqualChecker{

	public boolean mExe(
			Node oneNode,
			Node anoNode
		)throws Exception{
		
		if(oneNode.mNodeType != anoNode.mNodeType){
			return false;
		}
		if(oneNode.mNodeType == NULL_NODE){
			return true;
		}
		if(oneNode.mNodeType == NUMBER_NODE){
			
			double diff = oneNode.mCoef - anoNode.mCoef;
			
			if(Math.abs(diff) < DOUBLE_THRESHOLD){
				return true;
			}else{
				return false;
			}
		}
		if(oneNode.mNodeType == MONO_NODE){

			double diff = oneNode.mCoef - anoNode.mCoef;
			
			if(Math.abs(diff) < DOUBLE_THRESHOLD){
				
				if(oneNode.mToken.equals(anoNode.mToken) 
				&& oneNode.mPower == anoNode.mPower){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		if(oneNode.mNodeType == MULTI_NODE
		|| oneNode.mNodeType == POLY_NODE
		|| oneNode.mNodeType == BRACKET_NODE
		|| oneNode.mNodeType == EQU_NODE){

			double diff = oneNode.mCoef - anoNode.mCoef;
			
			if(Math.abs(diff) < DOUBLE_THRESHOLD){
				
				for(int cnt = 0; cnt < oneNode.mSubNodes.size(); cnt++){
					
					Node oneSub = oneNode.mGetSubNode(cnt);
					
					Node anoSub = anoNode.mGetSubNode(cnt);
					
					if(oneSub.mEquals(anoSub) == false){
						return false;
					}
				}
				
				return true;
			}else{
				return false;
			}
		}else{
			throw new Exception();
		}
	}
}
