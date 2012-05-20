package iabcd.com;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop  extends Thread
{
	 private boolean running;
	 private SurfaceHolder surfaceHolder;
	 private Tela tela;
	 private final static int  MAX_FPS = 50;
	 private final static int  MAX_FRAME_SKIP = 5;
	 private final static int FRAME_PERIOD = 1000/MAX_FPS;
	 
	 private static final String TAG = GameLoop.class.getSimpleName();
	 
	 public GameLoop(SurfaceHolder surfaceHolder, Tela tela)
	 { 
		 super();
		 this.surfaceHolder = surfaceHolder;
		 this.tela = tela;
	 }
	 
	 public void setRunning(boolean running) 
	 {
		  this.running = running;
	 }
	 
	 @Override
	 public void run() 
	 {
		 Log.d(TAG,"Rodando loop");
		 Canvas canvas;
		 
		 long beginTime;
		 long timeDiff;
		 int sleepTime;
		 int framesSkipped;
		 
		  while (running)
		  {
			  canvas = null;
			  try
			  {
				  canvas = this.surfaceHolder.lockCanvas();
				  
				  synchronized(surfaceHolder)
				  {
					  beginTime = System.currentTimeMillis();
					  framesSkipped = 0;
					  
					  this.tela.update();
					  
					  this.tela.onDraw(canvas);
					  
					  timeDiff = System.currentTimeMillis() - beginTime;
					  
					  sleepTime = (int)(FRAME_PERIOD - timeDiff);
					  
					  if(sleepTime > 0)
					  {
						  try
						  {
							  Thread.sleep(sleepTime);
						  }catch (InterruptedException e){}
					  }
					  
					  while((sleepTime < 0) && (framesSkipped < MAX_FRAME_SKIP))
					  {
						  this.tela.update();
						  
						  sleepTime += FRAME_PERIOD;
						  framesSkipped++;
						  
					  }
					  
				  }
				  
			  }
			  finally
			  {
				  if(canvas != null)
				  {
					  surfaceHolder.unlockCanvasAndPost(canvas);
				  }
			  }
		   // update game state
		   // render state to the screen
		  }
	 }

}
