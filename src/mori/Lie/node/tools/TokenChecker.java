package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

/*
 * @brief Token�Ɋ܂܂�Ă镶�����p���Ă��邩�A��������B
 */
public class TokenChecker{

	public boolean mExe(Node arg)throws Exception{
		
		if(arg.mNodeType == NULL_NODE
		|| arg.mNodeType == NUMBER_NODE){
			
			return true;
		}else
		if(arg.mNodeType == MONOMIAL_NODE){
		
			return mTokens.mCheck(arg.mToken);
		}else
		if(arg.mNodeType == MULTINOMIAL_NODE
		|| arg.mNodeType == POLYNOMIAL_NODE
		|| arg.mNodeType == BRACKET_NODE
		|| arg.mNodeType == EQU_NODE){
			
			boolean valid = true;
			for(int cnt = 0; cnt < arg.mSubNodes.size(); cnt++){
				
				Node subNode = (Node)arg.mSubNodes.elementAt(cnt);
				
				valid = mExe(subNode);
					
				if(valid == false){
					return false;
				}
			}
			
			return true;
		}else{
			throw new Exception();
		}
	}
}
