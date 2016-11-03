package mori.Lie.cmd;

import mori.Lie.Lie;
import mori.Lie.Node;
import mori.Lie.node.tools.DetailPrinter;

public class DetailCmd extends Lie implements I_Command  {

	private static DetailPrinter mDetailPrinter = new DetailPrinter();
	
	@Override
	public boolean mExe(String aStr) throws Exception {

		// 01234567
		// detail
		String noStr = aStr.substring(7);
		
		int no = Integer.parseInt(noStr.trim());
		
		Node equ = mEquations.mGet(no);
		
		mDetailPrinter.mExe(equ, "");
		
		return true;
	}
}
