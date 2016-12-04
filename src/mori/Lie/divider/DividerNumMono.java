package mori.Lie.divider;

import static mori.Lie.Node.NUMBER_NODE;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class DividerNumMono {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		aDestNode.mNodeType = MONO_NODE;
		
		aDestNode.mCoef = aOneNode.mCoef / aAnoNode.mCoef;
		
		aDestNode.mToken = new String( aAnoNode.mToken );
		
		aDestNode.mPower = - aAnoNode.mPower;
	}
}
