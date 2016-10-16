package mori.Lie.Command;

import mori.Lie.Lie;
import mori.Lie.Node;
import mori.Lie.NodeTools.Printer;
import mori.Lie.adder.Adder;

public class AddCmd extends Lie implements I_Command {
	
	private static Adder mAdder = new Adder();
	
	private static Printer mPrinter = new Printer();
	
	@Override
	public boolean mExe(String aStr) throws Exception {
		String[] parts = aStr.split("\\+");
		
		int leftNo = Integer.parseInt(parts[0].trim());
		
		int rightNo = Integer.parseInt(parts[1].trim());
		
		Node leftEqu = mEquations.mGet(leftNo);
		
		Node rightEqu = mEquations.mGet(rightNo);
		
		Node ans = mAdder.mExe(leftEqu, rightEqu);
				
		mEquations.add(ans);

		mPrinter.mExeln(ans);
		
		return true;
	}

}
