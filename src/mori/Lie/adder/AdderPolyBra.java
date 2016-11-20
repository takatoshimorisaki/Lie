package mori.Lie.adder;

import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.Lie.node.tools.EqualChecker;
import mori.Lie.node.tools.Factory;

public class AdderPolyBra {

	private static EqualChecker mEqualChecker = new EqualChecker();

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
	
		if(aOneNode.mNodeType == POLY_NODE){
			
			mExePolyBra(aDestNode, aOneNode, aAnoNode);
			
		}else{

			mExePolyBra(aDestNode, aAnoNode, aOneNode);
		}
	}

	private void mExePolyBra(
		Node aDestNode,
		Node aPoly,
		Node aBra
	)throws Exception{
		
		mFactory.mCopy(aDestNode, aPoly);
		
		boolean added = false;
		
		int size = aDestNode.mSubNodes.size();
		
		for(int destId = 0; destId < size; destId++){
			
			Node node = aDestNode.mGetSubNode(destId);
			
			if(node.mNodeType == BRACKET_NODE
			&& mEqualChecker.mExe(node, aBra)){
				
				node.mCoef += 1.0;

				if(Math.abs(node.mCoef) < DOUBLE_THRESHOLD){
					
					aDestNode.mSubNodes.remove(destId);
					
					size = aDestNode.mSubNodes.size();
				}
				
				added = true;
				
				break;
			}
		}
		
		if(added == false){
			
			aDestNode.add(aBra);
		}
	}
}
