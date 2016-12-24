package mori.Lie.adder;

import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;
import mori.Lie.node.tools.Factory;

public class AdderNumPoly {

	private static Factory mFactory = new Factory();
	
	public Node mExe(
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		Node ans = new Node();
		
		mExe(ans, aOneNode, aAnoNode);
		
		return ans;
	}

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		if(aOneNode.mNodeType == NUMBER_NODE
		|| aAnoNode.mNodeType == POLY_NODE){
			
			mExeNumPoly(aDestNode, aOneNode, aAnoNode);
			
		}else
		if(aOneNode.mNodeType == POLY_NODE
		|| aAnoNode.mNodeType == NUMBER_NODE){
			
			mExeNumPoly(aDestNode, aAnoNode, aOneNode);
			
		}else{
			throw new Exception();
		}
	}

	public void mExeNumPoly(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		mFactory.mCopy(aDestNode, aAnoNode);
		
		int size = aDestNode.mSubNodes.size();
		
		boolean added = false;
		
		for(int cnt = 0; cnt < size; cnt++){
			
			Node node = aDestNode.mGetSubNode(cnt);
			
			if(node.mNodeType == NUMBER_NODE){
				
				node.mCoef += aOneNode.mCoef;

				if(Math.abs(node.mCoef) < DOUBLE_THRESHOLD){
					
					aDestNode.mSubNodes.remove(cnt);
					
					size = aDestNode.mSubNodes.size();
				}
				
				added = true;
			}
		}
		
		if(added == false){
			
			aDestNode.add(aOneNode);
		}
	}
}
