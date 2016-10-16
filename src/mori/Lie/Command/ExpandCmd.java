package mori.Lie.Command;

import mori.Lie.*;

public class ExpandCmd extends Node implements I_Command {

	@Override
	public boolean mExe(String aStr) throws Exception {
		
		// 012345
		// expand
		String noStr = aStr.substring(6);
		
		int no = Integer.parseInt(noStr.trim());
		
		mEquations.mExpand(no);
		
		return true;
	}

}
