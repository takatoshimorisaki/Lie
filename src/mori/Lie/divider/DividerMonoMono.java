package mori.Lie.divider;

import static mori.Lie.divider.Divider.*;
import static mori.Lie.Node.*;
import mori.Lie.Node;

public class DividerMonoMono {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode,
		int  aLeftRight
	)throws Exception{

		aDestNode.mCoef = aOneNode.mCoef / aAnoNode.mCoef;
		
		if(aOneNode.mToken.equals(aAnoNode.mToken)){
			
			aDestNode.mPower = aOneNode.mPower - aAnoNode.mPower;
			
			if(aDestNode.mPower == 0){
				
				aDestNode.mToken = null;
				
				aDestNode.mPower = 1;
				
				aDestNode.mNodeType = NUMBER_NODE;
				
			}else{
				
				aDestNode.mNodeType = MONO_NODE;
			}
		}else{
			
			aDestNode.mToken = null;
			
			aDestNode.mPower = 1;
			
			aDestNode.mSubNodes = new java.util.Vector<Node>();
			
			Node oneSubNode = new Node();
			
			oneSubNode.mToken = new String( aOneNode.mToken );
			
			oneSubNode.mPower = aOneNode.mPower;
						
			Node anoSubNode = new Node();
			
			anoSubNode.mToken = new String( aAnoNode.mToken );
			
			anoSubNode.mPower = -aAnoNode.mPower;
			
			if(aLeftRight == DIVIDER_LEFT){

				aDestNode.add(anoSubNode);

				aDestNode.add(oneSubNode);
				
			}else if(aLeftRight == DIVIDER_RIGHT){

				aDestNode.add(oneSubNode);

				aDestNode.add(anoSubNode);
				
			}else{
				throw new Exception();
			}
			
			aDestNode.mNodeType = MULTI_NODE;
		}
	}
}
