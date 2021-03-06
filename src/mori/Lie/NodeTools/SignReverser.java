package mori.Lie.NodeTools;

import static mori.Lie.Node.*;

import mori.Lie.Node;

public class SignReverser {

	private Factory mFactory = new Factory();
	
	public void mExe(
			Node arg
		)throws Exception{
		
		if(arg.mNodeType == NULL_NODE){
			
			throw new Exception();
		}

		if(arg.mNodeType == NUMBER_NODE
		|| arg.mNodeType == MONOMIAL_NODE
		|| arg.mNodeType == MULTINOMIAL_NODE){
			
			arg.mCoef *= -1.0;
			
		}else
		if(arg.mNodeType == POLYNOMIAL_NODE
		|| arg.mNodeType == EQU_NODE){
			
			for(int cnt = 0; cnt < arg.mSubNodes.size(); cnt++){
				
				Node node = arg.mGetSubNode(cnt);
				
				this.mExe(node);
			}
		}else
		if(arg.mNodeType == BRACKET_NODE){
			
			Node node0 = arg.mGetSubNode(0);
			
			Node node1 = arg.mGetSubNode(1);
			
			arg.mSubNodes.clear();
			
			arg.add(node1);
			
			arg.add(node0);
		}else{

			throw new Exception();
		}
	}
}
