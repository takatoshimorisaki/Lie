package mori.Lie.cmd;

import static mori.Lie.node.tools.Holder.mSpaceSplitter;
import mori.Lie.Node;

public class FactorCmd extends Node implements I_Command {

	@Override
	public boolean mExe(String aStr) throws Exception {
		
		// 012345
		// factor
		String noStr = aStr.substring(6);
				
		int no = Integer.parseInt(noStr.trim());
			
		mEquations.mFactor(no);
	
		return true;
	}
}
