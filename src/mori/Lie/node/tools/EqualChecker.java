package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
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
				&& oneNode.mPower.mEquals(anoNode.mPower)){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		if(oneNode.mNodeType == MULTI_NODE
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
		}else
		if(oneNode.mNodeType == POLY_NODE){
			
			for(int oneCnt = 0; oneCnt < oneNode.mSubNodes.size(); oneCnt++){
				
				boolean finded     = false;
				Node    oneSubNode = oneNode.mGetSubNode(oneCnt);
				
				for(int anoCnt = 0; anoCnt < anoNode.mSubNodes.size(); anoCnt++){
					
					Node anoSubNode = anoNode.mGetSubNode(anoCnt);
					
					boolean isEqual = this.mExe(oneSubNode, anoSubNode);
					
					if(isEqual){
						finded = true;
						
						break;
					}
				}
				
				if(finded == false){
					return false;
				}
			}
			
			return true;
			
		}else{
			throw new Exception();
		}
	}
}
