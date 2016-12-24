package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;

/*
 * @brief Tokenに含まれてる文字列を用いているか、検査する。
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
