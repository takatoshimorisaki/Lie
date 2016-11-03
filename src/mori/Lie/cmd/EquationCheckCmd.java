package mori.Lie.cmd;

public class EquationCheckCmd extends mori.Lie.Lie implements I_Command{

	public boolean mExe(String arg)throws Exception{
		
		// 01234
		// check
		if(arg.length() <= 6){
			
			mEquations.mCheck();
		}else{
			
			String cmdStr = arg.substring(5).trim();
			
			int id = Integer.parseInt(cmdStr);
			
			mEquations.mCheck(id);
		}
		
		return true;
	}
}
