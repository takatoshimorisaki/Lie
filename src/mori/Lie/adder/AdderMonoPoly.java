package mori.Lie.adder;

import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.Lie.node.tools.EqualNomialChecker;
import mori.Lie.node.tools.Factory;

public class AdderMonoPoly {

	private static EqualNomialChecker mEqualNomialChecker = new EqualNomialChecker();

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
	
		if(aOneNode.mNodeType == MONO_NODE
		|| aAnoNode.mNodeType == POLY_NODE){
			
			mExeMonoPoly(aDestNode, aOneNode, aAnoNode);
			
		}else{
			
			mExeMonoPoly(aDestNode, aAnoNode, aOneNode);
		}
	}

	private void mExeMonoPoly(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		mFactory.mCopy(aDestNode, aAnoNode);
		
		int size = aDestNode.mSubNodes.size();
		
		boolean added = false;
		
		for(int id = 0; id < size; id++){
			
			Node node = aDestNode.mGetSubNode(id);
			
			if(node.mNodeType == MONO_NODE
			&& mEqualNomialChecker.mExe(aOneNode, node)){
				
				node.mCoef += aOneNode.mCoef;

				if(Math.abs(node.mCoef) < DOUBLE_THRESHOLD){
					
					aDestNode.mSubNodes.remove(id);
					
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
