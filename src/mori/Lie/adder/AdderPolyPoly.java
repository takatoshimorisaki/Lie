package mori.Lie.adder;


import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.Lie.node.tools.EqualChecker;
import mori.Lie.node.tools.EqualNomialChecker;
import mori.Lie.node.tools.Factory;

public class AdderPolyPoly {

	private static EqualNomialChecker mEqualNomialChecker = new EqualNomialChecker();
	
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
	
		mFactory.mCopy(aDestNode, aOneNode);
		
		int anoSize = aAnoNode.mSubNodes.size();
		
		for(int anoId = 0; anoId < anoSize; anoId++){
			
			Node anoSubNode = aAnoNode.mGetSubNode(anoId);
			
			boolean added = false;
			
			int destSize = aDestNode.mSubNodes.size();
			
			for(int destId = 0; destId < destSize; destId++){
				
				Node destSubNode = aDestNode.mGetSubNode(destId);
				
				if(anoSubNode.mNodeType == BRACKET_NODE){
					if(mEqualChecker.mExe(anoSubNode, destSubNode) == true){
						
						destSubNode.mCoef += anoSubNode.mCoef;
						
						added = true;
						
						break;
					}
				}else{
					if(mEqualNomialChecker.mExe(anoSubNode, destSubNode) == true){
						
						destSubNode.mCoef += anoSubNode.mCoef;
						
						added = true;
						
						break;
					}
				}
			}
			
			if(added == false){
				
				aDestNode.add(anoSubNode);
			}
		}// for anoId
	}
}
