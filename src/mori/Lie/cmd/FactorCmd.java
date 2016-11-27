package mori.Lie.cmd;

import static mori.Lie.node.tools.Holder.mSpaceSplitter;
import mori.Lie.Node;

public class FactorCmd extends Node implements I_Command {

	@Override
	public boolean mExe(String aStr) throws Exception {
		
		// 012345
		// factor
		String noStr = aStr.substring(6);
		
		String[] parts = mSpaceSplitter.mExe(noStr);
		
		int no = Integer.parseInt(parts[0].trim());
		
		if(parts == null){
			
			throw new Exception(aStr);
			
		}else
		if(parts.length == 1){
		
			mEquations.mFactor(no, "lr");
		
		}else{
		
			String option = parts[1].trim();
			
			if(option.equals("lr")){

				mEquations.mFactor(no, "lr");
				
			}else if(option.equals("l")){

				mEquations.mFactor(no, "l");
				
			}else if(option.equals("r")){

				mEquations.mFactor(no, "r");
				
			}else{

				mEquations.mFactor(no, "lr");
			}
		}
		
		return true;
	}
}
