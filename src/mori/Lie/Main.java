package mori.Lie;

public class Main implements Runnable{
	
	private mori.LogService.C_LogService mLog;
	
	//! �X���b�h
	private Thread mO_Thread;
	
	private Parser mParser;

	public Main(
		mori.LogService.C_LogService log
	){
		mLog = log;
		
		mParser = new Parser();
	}
	
	public void mStartService(){

		mO_Thread = new Thread(this);

		mO_Thread.start();
	}
	
	public void run(){
		
		while(true){
			
			String req = mLog.mGetReq();
			
			if(req != null){
				mParser.add(req);
			}
			
			try{
				Thread.sleep(1000);
			}catch(Exception ex){
			}
		}
	}
}

