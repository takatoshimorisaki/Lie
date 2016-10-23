package mori.Lie.NodeTools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class EqualNomialChecker{

	public boolean mExe(
			Node oneNode,
			Node anoNode
		)throws Exception{
		
		if(oneNode.mNodeType != anoNode.mNodeType){
			return false;
		}else
		if(oneNode.mNodeType == NULL_NODE){
			return false;
		}else
		if(oneNode.mNodeType == NUMBER_NODE){
			return false;
		}else
		if(oneNode.mNodeType == MONO_NODE){
			
			if(oneNode.mToken.equals(anoNode.mToken) 
			&& oneNode.mPower == anoNode.mPower){
				return true;
			}else{
				return false;
			}
		}else
		if(oneNode.mNodeType == MULTI_NODE){
			
			for(int cnt = 0; cnt < oneNode.mSubNodes.size(); cnt++){
				
				Node oneSub = oneNode.mGetSubNode(cnt);
				
				Node anoSub = anoNode.mGetSubNode(cnt);
				
				if(oneSub.mEquals(anoSub) == false){
					return false;
				}
			}
			
			return true;

		}else{
			throw new Exception();
		}
	}
}

