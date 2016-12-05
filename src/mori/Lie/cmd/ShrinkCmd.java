package mori.Lie.cmd;

import static mori.Lie.node.tools.Holder.mSpaceSplitter;
import mori.Lie.Node;

public class ShrinkCmd extends Node implements I_Command {

	@Override
	public boolean mExe(String aStr) throws Exception {
		
		// 012345
		// shrink
		String noStr = aStr.substring(6);
				
		int no = Integer.parseInt(noStr.trim());
			
		mEquations.mShrink(no);
	
		return true;
	}
}
