package mori.Lie.expander;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class Expander{

	private static ExpanderMono mExpanderMono = new ExpanderMono();
	
	private static ExpanderMultiMono mExpanderMultiMono = new ExpanderMultiMono();
	
	private static ExpanderMultiMulti mExpanderMultiMulti = new ExpanderMultiMulti();
	
	private static ExpanderPolyMono mExpanderPolyMono = new ExpanderPolyMono();
	
	private static ExpanderPolyPoly mExpanderPolyPoly = new ExpanderPolyPoly();
	
	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node equ
	)throws Exception{
		boolean expanded = false;

		Node leftNode = equ.mGetSubNode(0);
		
		Node rightNode = equ.mGetSubNode(1);
		
		expanded = mExe(aDestNode, arg, leftNode, rightNode);

		return expanded;
	}

	public boolean mExe(
		Node aDestNode,
		Node arg,
		Node leftNode,
		Node rightNode
	)throws Exception{
		boolean expanded = false;
		
		if(arg.mNodeType == NULL_NODE
		|| arg.mNodeType == NUMBER_NODE){
			// nothing to do.
		}else 
		if(arg.mNodeType == MONOMIAL_NODE
		&& leftNode.mNodeType == MONOMIAL_NODE){
			
			expanded = mExpanderMono.mExe(aDestNode, arg, leftNode, rightNode);
			
		}else
		if(arg.mNodeType == MULTI_NODE){
			
			if(leftNode.mNodeType == MONOMIAL_NODE){

				expanded = mExpanderMultiMono.mExe(aDestNode, arg, leftNode, rightNode);
					
			}else
			if(leftNode.mNodeType == MULTI_NODE){

				expanded = mExpanderMultiMulti.mExe(aDestNode, arg, leftNode, rightNode);
					
			}else{
				// nothing to do.
			}

		}else
		if(arg.mNodeType == POLY_NODE){

			if(leftNode.mNodeType == MONOMIAL_NODE
			|| leftNode.mNodeType == MULTI_NODE){
				
				expanded = mExpanderPolyMono.mExe(aDestNode, arg, leftNode, rightNode, this);
				
			}else
			if(leftNode.mNodeType == POLY_NODE){
				
				expanded = mExpanderPolyPoly.mExe(aDestNode, arg, leftNode, rightNode);
				
			}else{
				// nothing to do.
			}
		}else{
			String errMsg = String.format("arg %s", arg.toNodeType());
			
			throw new Exception(errMsg);
		}
		
		return expanded;
	}
}
