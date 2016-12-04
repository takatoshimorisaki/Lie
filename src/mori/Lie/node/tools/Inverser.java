package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class Inverser {

	public final static int INVERSER_LEFT = 1;
	
	public final static int INVERSER_RIGHT = 2;
	
	public Node mExe(
		Node aSrcNode,
		int  aLeftRight
	)throws Exception{
		Node ans = new Node();
		
		if(aSrcNode.mNodeType == NULL_NODE){
			
			return ans;
			
		}else if(aSrcNode.mNodeType == NUMBER_NODE){
			
			ans.mNodeType = NUMBER_NODE;
			
			ans.mCoef = 1.0 / aSrcNode.mCoef;
			
			return ans;

		}else if(aSrcNode.mNodeType == MONO_NODE){
			
			ans.mNodeType = MONO_NODE;

			ans.mCoef = 1.0 / aSrcNode.mCoef;
			
			ans.mToken = new String( aSrcNode.mToken );
			
			ans.mPower = - aSrcNode.mPower;
			
			return ans;

		}else if(aSrcNode.mNodeType == MULTI_NODE){
			
			ans.mNodeType = MULTI_NODE;

			ans.mCoef = 1.0 / aSrcNode.mCoef;
			
			if(aLeftRight == INVERSER_LEFT){
				
				for(int id = 0; id < aSrcNode.mSubNodes.size(); id++){

					Node node = aSrcNode.mGetSubNode(id);
					
					Node invNode = mExe(node, aLeftRight);
					
					ans.add(invNode);
				}
				
			}else if(aLeftRight == INVERSER_RIGHT){

				for(int id = aSrcNode.mSubNodes.size() - 1; id >= 0; id--){
					
					Node node = aSrcNode.mGetSubNode(id);
					
					Node invNode = mExe(node, aLeftRight);
					
					ans.add(invNode);
				}
				
			}else{
				throw new Exception();
			}
			
			return ans;
			
		}else{
			throw new Exception();
		}
	}
}
