package iabcd.com;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Balao 
{
	private int x;
	private int y;
	private boolean visivel = false;
	private AlertDialog.Builder alert;
	private View caixa;
	private LayoutInflater factory;
	private String objeto;
	private EditText editText;
	
	
	public Balao(int x, int y, Context context)
	{
		this.x = x;
		this.y = y;
		
		factory = LayoutInflater.from(context);
		
		caixa = factory.inflate(R.layout.main, null);
		
		alert = new AlertDialog.Builder(context);
		
		alert.setTitle("Digite o nome do item a ser usado");
		
		editText = (EditText)caixa.findViewById(R.id.editText1);
		
		alert.setCancelable(false);

		
	}
	
	public void botoes()
	{
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				objeto = editText.getText().toString();
				
				((ViewGroup)caixa.getParent()).removeView(caixa);
				
				Log.v("Objeto", objeto);
				
				visivel = false;
				
			}
		});
		
		alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				((ViewGroup)caixa.getParent()).removeView(caixa);
		
				visivel = false;
				
			}
		});
		
		
	}
	
	
	
	public void draw()
	{
		if(visivel == false)
		{
			
			alert.setView(caixa);
			alert.show();
			visivel = true;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}


}
