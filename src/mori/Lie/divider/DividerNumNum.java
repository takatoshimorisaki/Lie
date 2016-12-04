package mori.Lie.divider;

import static mori.Lie.Node.*;
import mori.Lie.Node;

public class DividerNumNum {

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception{
		
		aDestNode.mNodeType = NUMBER_NODE;
		
		aDestNode.mCoef = aOneNode.mCoef / aAnoNode.mCoef;
	}
}
