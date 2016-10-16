package mori.Lie.Command;

import mori.Lie.Lie;
import mori.Lie.Node;
import mori.Lie.NodeTools.Printer;

public class MultiplyCmd extends Lie implements I_Command {

	private static mori.Lie.NodeTools.Multiplier mMultiplier = new mori.Lie.NodeTools.Multiplier();

	private static mori.Lie.NodeTools.Together mTogether = new mori.Lie.NodeTools.Together(); 
			
	private static Printer mPrinter = new Printer();
	
	@Override
	public boolean mExe(String aStr) throws Exception {
		String[] parts = aStr.split("\\*");
		
		int leftNo = Integer.parseInt(parts[0].trim());
		
		int rightNo = Integer.parseInt(parts[1].trim());
		
		Node leftEqu = mEquations.mGet(leftNo);
		
		Node rightEqu = mEquations.mGet(rightNo);
		
		Node ans = mMultiplier.mExe(leftEqu, rightEqu);
		
		Node togetheredNode = new Node();
		
		boolean togethered = mTogether.mExe(togetheredNode, ans);
		
		mEquations.add(togetheredNode);

		mPrinter.mExeln(togetheredNode);
		
		return true;
	}

}
