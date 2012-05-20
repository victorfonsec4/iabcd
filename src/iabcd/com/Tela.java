package iabcd.com;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Tela extends SurfaceView implements
SurfaceHolder.Callback
{
	private static final String TAG = Tela.class.getSimpleName();
	private Objeto chave;
	private GameLoop loop;
	private Balao balao;
	private Context context;
    private Paint paint;
    private Inventory inventory;
	
	public Tela(Context context)
	{
		super(context);
		
		this.context = context;
		
		getHolder().addCallback(this);
		
		setFocusable(true);
		
		chave = new Objeto(BitmapFactory.decodeResource(getResources(), R.drawable.key), 100, 100);
		
		loop = new GameLoop(getHolder(), this);
		
		balao = new Balao(20, 20, context);
		balao.botoes();
		
		paint = new Paint();
		
		inventory = new Inventory();
	}
	
	@Override
	 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	 {
	 }
	
	 @Override
	 public void surfaceCreated(SurfaceHolder holder) 
	 {
		 Log.d(TAG,"Criando superfície");
		 loop.setRunning(true);
		 loop.start();
	 }
	 
	 @Override
	 public void surfaceDestroyed(SurfaceHolder holder)
	 {
		 Log.d(TAG,"Destruindo superfície");
		 boolean retry = true;
		 while(retry)
		 {
			 try
			 {
				 loop.join();
				 retry = false;
			 }
			 catch(InterruptedException e)
			 {
				 //try again shutting down the thread
			 }
		 }

	 }
	 
	 @Override
	 public boolean onTouchEvent(MotionEvent event)
	 {		 
		 //balao.draw();
		 
		 if(event.getAction() == MotionEvent.ACTION_DOWN)
		 {
			 Log.d(TAG,"X="+event.getX()+" Y="+event.getY());
			 
			 balao.setX((int) event.getX());
			 balao.setY((int) event.getY());
			 if( chave.isTouch( (int) event.getX(), (int) event.getY() ) )
			 {
				 if(!chave.isPicked())
					 inventory.setNumItens(inventory.getNumItens() + 1);
				 
				 chave.onTouch(inventory.getNumItens(), inventory.getItemWidth(), inventory.getItemHeight());
			 }
			 
		 }
		 return true;
	 }
	 
	 @Override
	 protected void onDraw(Canvas canvas) 
	 {
		 paint.setColor(Color.YELLOW);
		 canvas.drawColor(Color.BLUE);
		 
		 canvas.drawLine(0.0f, canvas.getHeight() - 68, canvas.getWidth(), canvas.getHeight() - 68, paint);
		 
		 chave.draw(canvas);

	 }
	 
	 public void update()
	 {

	 }

}
