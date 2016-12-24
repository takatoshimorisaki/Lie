package mori.Lie.node.tools;

import static mori.Lie.multiplier.Holder.mMultiplier;
import static mori.Lie.node.tools.Holder.mInverser;

import mori.Lie.I_Operator;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class Divider implements I_Operator{

	public final static int DIVIDER_LEFT = 1;
	
	public final static int DIVIDER_RIGHT = 2;

	public Node mExe(
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		return mExe(aOneNode, aAnoNode, DIVIDER_RIGHT);
	}

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{

		mExe(aDestNode, aOneNode, aAnoNode, DIVIDER_RIGHT);
	}
	
	public Node mExe(
		Node aOneNode,
		Node aAnoNode,
		int  aLeftRight
	)throws Exception{
		Node ans = new Node();
		
		mExe(ans, aOneNode, aAnoNode, aLeftRight);
		
		return ans;
	}

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
	}
}
