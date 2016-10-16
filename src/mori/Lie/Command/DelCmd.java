package mori.Lie.Command;

import mori.Lie.Lie;

public class DelCmd extends Lie implements I_Command  {

	@Override
	public boolean mExe(String aStr) throws Exception {

		// 012345
		// del
		String noStr = aStr.substring(4);
		
		int no = Integer.parseInt(noStr.trim());
		
		mEquations.mRemove(no);
		
		return true;
	}
}
