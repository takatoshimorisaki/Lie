package mori.Lie.node.parser;

import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;

public class NodeTypeChecker {

	public static boolean mIsNomialNode(Node arg){
		
		if(arg.mNodeType == NUMBER_NODE
		|| arg.mNodeType == MONO_NODE
		|| arg.mNodeType == MULTI_NODE
		|| arg.mNodeType == POLY_NODE
		|| arg.mNodeType == BRACKET_NODE){
			
			return true;
			
		}else{
			
			return false;
		}
	}

	public static boolean mIsOperatorNode(Node arg){
		
		if(arg.mNodeType == OPE_ADD_NODE
		|| arg.mNodeType == OPE_SUB_NODE
		|| arg.mNodeType == OPE_MULTI_NODE
		|| arg.mNodeType == OPE_POWER_NODE){
			
			return true;
			
		}else{
			
			return false;
		}			
	}
}
