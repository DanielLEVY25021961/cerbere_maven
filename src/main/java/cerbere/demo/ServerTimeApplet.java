package cerbere.demo;

import java.applet.Applet;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerTimeApplet extends Applet {
	
    /**
     * serialVersionUID : long :<br/>
     * .<br/>
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * resultField : TextField :<br/>
     * .<br/>
     */
    private TextField resultField = new TextField(20);
    
    /**
     * sendButton : Button :<br/>
     * .<br/>
     */
    private Button sendButton = new Button("Heure");

    

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    	
        add(this.resultField);
        add(this.sendButton);

        this.sendButton.addActionListener(new ActionListener() {
        	
            /**
             * {@inheritDoc}
             */
        	@Override
            public void actionPerformed(ActionEvent pE) {
        		ServerTimeApplet.this.getResultField().setText(getTime());
            }
        });

        this.resultField.setEditable(false);
    }

    
    
    /**
     * method getTime() :<br/>
     * .<br/>
     * <br/>
     *
     * @return : String :  .<br/>
     */
    public String getTime() {
        try {
            URL urlServlet = new URL(getCodeBase(), "demo.action?action=16&time=1");
            HttpURLConnection urlConnection = (HttpURLConnection) urlServlet.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            ObjectInputStream inputFromServlet = new ObjectInputStream(inputStream);
            String time = (String) inputFromServlet.readObject();
            
            return time;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERR";
        }
    }



	
	/**
	 * method getResultField() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return resultField : TextField.<br/>
	 */
	public final TextField getResultField() {
		return this.resultField;
	}



	
	/**
	* method setResultField(
	* TextField pResultField) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pResultField : TextField : valeur à passer à resultField.<br/>
	*/
	public final void setResultField(TextField pResultField) {
		this.resultField = pResultField;
	}



	
	/**
	 * method getSendButton() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return sendButton : Button.<br/>
	 */
	public final Button getSendButton() {
		return this.sendButton;
	}



	
	/**
	* method setSendButton(
	* Button pSendButton) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pSendButton : Button : valeur à passer à sendButton.<br/>
	*/
	public final void setSendButton(Button pSendButton) {
		this.sendButton = pSendButton;
	}
    
    
    
    
    
}
