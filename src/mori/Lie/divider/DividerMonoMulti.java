package mori.Lie.divider;

import static mori.Lie.divider.Divider.*;
import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mInverser;
import mori.Lie.Node;

public class DividerMonoMulti {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode,
		int  aLeftRight
	)throws Exception{

		Node invNode = mInverser.mExe(aAnoNode, aLeftRight);
		
		if(aLeftRight == DIVIDER_LEFT){
		
			mMultiplier.mExe(aDestNode, invNode, aOneNode);
			
		}else if(aLeftRight == DIVIDER_RIGHT){

			mMultiplier.mExe(aDestNode, aOneNode, invNode);
			
		}else{
			throw new Exception();
		}
		
		throw new Exception();
	}
}
