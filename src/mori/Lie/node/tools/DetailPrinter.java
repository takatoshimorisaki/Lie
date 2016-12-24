package mori.Lie.node.tools;

import static java.lang.System.out;
import static mori.TabStop.*;
import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;

public class DetailPrinter {

	public void mExe(Node arg, String aSpace) throws Exception{
		
		if(arg.mNodeType == NULL_NODE){
			
			out.printf("%sNULL_NODE\n", aSpace);
		}else
		if(arg.mNodeType == NUMBER_NODE){
			
			out.printf("%sNUMBER_NODE\n", aSpace);
			
			out.printf("%smCoef %f\n", aSpace, arg.mCoef);
		}else
		if(arg.mNodeType == MONO_NODE){
			
			out.printf("%sMONO_NODE\n", aSpace);
			
			out.printf("%smCoef %f\n", aSpace, arg.mCoef);
			
			out.printf("%smToken %s\n", aSpace, arg.mToken);
			
			out.printf("%smPower %s\n", aSpace, arg.mPower.mToString());
		}else
		if(arg.mNodeType == MULTI_NODE
		|| arg.mNodeType == POLY_NODE
		|| arg.mNodeType == EQU_NODE
		|| arg.mNodeType == BRACKET_NODE){

			out.printf("%s%s\n", aSpace, arg.toNodeType());
			
			out.printf("%smCoef %f\n", aSpace, arg.mCoef);
			
			out.printf("%smToken %s\n", aSpace, arg.mToken);
			
			out.printf("%smPower %s\n", aSpace, arg.mPower.mToString());
			
			int size = arg.mSubNodes.size();
					
			out.printf("%smSubNodes.size() %d\n", aSpace, size);
			
			for(int cnt = 0; cnt < size; cnt++){
				
				Node subNode = arg.mGetSubNode(cnt);
				
				mExe(subNode, aSpace + SPACE4);
			}
		}else{
			throw new Exception();
		}
	}
}
