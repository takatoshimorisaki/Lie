package mori.Lie.expander;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class Expander{

	private static ExpanderMono mExpanderMono = new ExpanderMono();
	
	private static ExpanderMultiMono mExpanderMultiMono = new ExpanderMultiMono();
	
	private static ExpanderMultiMulti mExpanderMultiMulti = new ExpanderMultiMulti();
	
	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node equ
	)throws Exception{
		boolean expanded = false;
		
		Node rightNode = equ.mGetSubNode(0);
		
		Node leftNode = equ.mGetSubNode(1);
		
		expanded = mExe(aDestNode, arg, rightNode, leftNode);

		return expanded;
	}

	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node rightNode,
		Node leftNode
	)throws Exception{
		boolean expanded = false;
		
		if(arg.mNodeType == NULL_NODE
		|| arg.mNodeType == NUMBER_NODE){
			// nothing to do.
		}else 
		if(arg.mNodeType == MONOMIAL_NODE
		&& rightNode.mNodeType == MONOMIAL_NODE){
			
			expanded = mExpanderMono.mExe(aDestNode, arg, rightNode, leftNode);
			
		}else
		if(arg.mNodeType == MULTI_NODE){
			
			if(rightNode.mNodeType == MONOMIAL_NODE){

				expanded = mExpanderMultiMono.mExe(aDestNode, arg, rightNode, leftNode);
					
			}else
			if(rightNode.mNodeType == MULTI_NODE){

				expanded = mExpanderMultiMulti.mExe(aDestNode, arg, rightNode, leftNode);
					
			}else{
				// nothing to do.
			}
		}else{
			throw new Exception("not implemented.");
		}
		
		return expanded;
	}
}
